package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.models.Customer;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.models.Rental;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.DiscountRepository;
import com.example.rent2gojavaproject.repositories.RentalRepository;
import com.example.rent2gojavaproject.services.abstracts.RentalService;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;
import com.example.rent2gojavaproject.services.rules.RentalBusinessRules;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService mapperService;
    private final CarRepository carRepository;
    private final RentalBusinessRules businessRules;
    private final DiscountRepository discountRepository;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetRentalListResponse>> getAllRentals() {
        List<Rental> rentals = this.rentalRepository.findAll();
        List<GetRentalListResponse> responses = rentals.stream().map(rental -> this.mapperService.forResponse().map(rental, GetRentalListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetRentalListResponse>> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("isActiveFilterRental");
        filter.setParameter("isActive", isDeleted);
        Iterable<GetRentalListResponse> rentals = this.rentalRepository.findAll()
                .stream().map(rental -> this.mapperService.forResponse()
                        .map(rental, GetRentalListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter("isActiveFilterRental");
        return new SuccessDataResult<>(rentals,Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetRentalResponse> getById(int id) {
        Rental rental = this.rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));

        GetRentalResponse response = this.mapperService.forResponse().map(rental, GetRentalResponse.class);


        return new SuccessDataResult<>(response, Message.GET.getMessage());
    }

    @Override
    public Result addRental(AddRentalRequest addRentalRequest) {
        Car car = carRepository.findById(addRentalRequest.getCarId()).orElseThrow();

        String discountCode = addRentalRequest.getDiscountCode();
        Discount defaultDiscount = businessRules.getDiscountByCodeOrDefault(discountCode);

        businessRules.checkIfExistsById(addRentalRequest.getCarId(), addRentalRequest.getCustomerId(), addRentalRequest.getEmployeeId());
        businessRules.checkRentalPeriod(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());

        double totalPrice = businessRules.calculateTotalPrice(
                addRentalRequest.getStartDate(), addRentalRequest.getEndDate(),
                car.getDailyPrice(), defaultDiscount.getDiscountCode()
        );

        Rental rental = mapperService.forRequest().map(addRentalRequest, Rental.class);
        rental.setDiscount(defaultDiscount);
        rental.setTotalPrice(totalPrice);

        rental.setStartKilometer(car.getKilometer());
        rentalRepository.save(rental);

        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateRental(UpdateRentalRequest updateRentalRequest) {
        Car car = carRepository.findById(updateRentalRequest.getCarId()).orElseThrow();

        this.rentalRepository.findById(updateRentalRequest.getId()).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));

        this.businessRules.checkIfExistsById(updateRentalRequest.getCarId(), updateRentalRequest.getCustomerId(), updateRentalRequest.getEmployeeId());
        this.businessRules.checkRentalPeriod(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate());
        this.businessRules.checkIfKilometer(car.getKilometer(), updateRentalRequest.getEndKilometer());
        Rental rental = this.mapperService.forRequest().map(updateRentalRequest, Rental.class);
        car.setKilometer(rental.getEndKilometer());
        this.rentalRepository.save(rental);
        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteRental(int id) {
        Rental rental = this.rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        rental.setDeletedAt(LocalDate.now());
        this.rentalRepository.save(rental);
        this.rentalRepository.delete(rental);

        return new SuccessResult(Message.DELETE.getMessage());
    }
}
