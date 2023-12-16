package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.models.Rental;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.RentalRepository;
import com.example.rent2gojavaproject.services.abstracts.RentalService;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;
import com.example.rent2gojavaproject.services.rules.RentalBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService mapperService;
    private final CarRepository carRepository;
    private final RentalBusinessRules businessRules;


    @Override
    public DataResult<List<GetRentalListResponse>> getAllRentals() {
        List<Rental> rentals = this.rentalRepository.findAll();
        List<GetRentalListResponse> responses = rentals.stream().map(rental -> this.mapperService.forResponse()
                .map(rental, GetRentalListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetRentalListResponse>>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetRentalResponse> getById(int id) {
        Rental rental = this.rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));

        GetRentalResponse response = this.mapperService.forResponse().map(rental, GetRentalResponse.class);


        return new SuccessDataResult<GetRentalResponse>(response, Message.GET.getMessage());
    }

    @Override
    public Result addRental(AddRentalRequest addRentalRequest) {
        Car car = carRepository.findById(addRentalRequest.getCarId()).orElseThrow();

        this.businessRules.checkRentalPeriod(addRentalRequest.getStartDate(),addRentalRequest.getEndDate());

        double totalPrice = this.businessRules.calculateTotalPrice(addRentalRequest.getStartDate(), addRentalRequest.getEndDate(), car.getDailyPrice());

        Rental rental = this.mapperService.forRequest().map(addRentalRequest, Rental.class);
        rental.setTotalPrice(totalPrice);
        rental.setStartKilometer(car.getKilometer());

        this.rentalRepository.save(rental);
        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateRental(UpdateRentalRequest updateRentalRequest) {
        this.rentalRepository.findById(updateRentalRequest.getId()).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));

        Rental rental = this.mapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalRepository.save(rental);
        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteRental(int id) {
        this.rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));
        this.rentalRepository.deleteById(id);
        return new SuccessResult(Message.DELETE.getMessage());
    }
}
