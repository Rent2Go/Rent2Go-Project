package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.DiscountNotUniqueException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.constants.HibernateConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.models.Rental;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.RentalRepository;
import com.example.rent2gojavaproject.services.abstracts.RentalService;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.ReturnRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
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
    private EntityManager entityManager;


    @Override
    public DataResult<List<GetRentalListResponse>> getAllRentals() {

        List<Rental> rentals = this.rentalRepository.findAll();
        List<GetRentalListResponse> responses = rentals.stream()
                .map(rental -> this.mapperService.forResponse()
                        .map(rental, GetRentalListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetRentalListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(HibernateConstants.IS_ACTIVE_FILTER_RENTAL.getValue());

        filter.setParameter(HibernateConstants.IS_ACTIVE.getValue(), isActive);

        Iterable<GetRentalListResponse> rentals = this.rentalRepository.findAll()
                .stream().map(rental -> this.mapperService.forResponse()
                        .map(rental, GetRentalListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter(HibernateConstants.IS_ACTIVE_FILTER_RENTAL.getValue());

        return new SuccessDataResult<>(rentals, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetRentalResponse> getById(int id) {

        Rental rental = this.rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.RENTAL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetRentalResponse response = this.mapperService.forResponse().map(rental, GetRentalResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public DataResult<Integer> addRental(AddRentalRequest addRentalRequest) {
        Car car = carRepository.findById(addRentalRequest.getCarId()).orElseThrow();

        String discountCode = addRentalRequest.getDiscount().getDiscountCode();
        Discount defaultDiscount = businessRules.getDiscountByCodeOrDefault(discountCode);

        businessRules.checkIfExistsById(addRentalRequest.getCarId(), addRentalRequest.getCustomerId());
        businessRules.checkRentalPeriod(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());

        double totalPrice = businessRules.calculateTotalPrice(
                addRentalRequest.getStartDate(), addRentalRequest.getEndDate(),
                car.getDailyPrice(), defaultDiscount.getDiscountCode());

        Rental rental = mapperService.forRequest().map(addRentalRequest, Rental.class);
        rental.setDiscount(defaultDiscount);
        rental.setTotalPrice(totalPrice);

        rental.setStartKilometer(car.getKilometer());
        Rental result =rentalRepository.save(rental);

        return new SuccessDataResult(result.getId() , MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateRental(UpdateRentalRequest updateRentalRequest) {

        Car car = carRepository.findById(updateRentalRequest.getCarId()).orElseThrow();

        this.rentalRepository.findById(updateRentalRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.RENTAL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        this.businessRules.checkIfExistsById(updateRentalRequest.getCarId(), updateRentalRequest.getCustomerId(), updateRentalRequest.getEmployeeId());
        this.businessRules.checkRentalPeriod(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate());
        this.businessRules.checkIfKilometer(car.getKilometer(), updateRentalRequest.getEndKilometer());

        Rental rental = this.mapperService.forRequest().map(updateRentalRequest, Rental.class);
        car.setKilometer(rental.getEndKilometer());
        this.rentalRepository.save(rental);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result vehicleDelivery(ReturnRentalRequest returnRentalRequest){
        Rental rental = rentalRepository.findById(returnRentalRequest.getId()).orElseThrow();

        rental.setId(returnRentalRequest.getId());
        rental.setEndKilometer(returnRentalRequest.getEndKilometer());
        rental.setReturnDate(returnRentalRequest.getReturnDate());

        this.rentalRepository.save(rental);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteRental(int id) {

        Rental rental = this.rentalRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));
        rental.setDeletedAt(LocalDate.now());

        this.rentalRepository.save(rental);
        this.rentalRepository.delete(rental);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }

    @Override
    public boolean  findByCustomerIdAndDiscountId(int customerId, int discountId) {
        boolean result = this.rentalRepository.existsByCustomerIdAndDiscountId(customerId, discountId);

        if (result){
            throw new DiscountNotUniqueException(MessageConstants.DISCOUNT_NOT_UNIQUE.getMessage());
        }
        return result;
    }

    @Override
    public DataResult<List<GetRentalListResponse>> findByEmployeeId(int customerId) {
        List<Rental> findEmployeeRentals = this.rentalRepository.findByCustomerId(customerId);
        List<GetRentalListResponse> responses =
                findEmployeeRentals.stream()
                        .map(rental -> this.mapperService.forResponse()
                                .map(rental,GetRentalListResponse.class))
                        .collect(Collectors.toList());


        return new SuccessDataResult<>(responses,MessageConstants.GET_ALL.getMessage());
    }
}
