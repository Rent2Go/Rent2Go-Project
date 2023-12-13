package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;

    private ModelMapperService mapperService;


    @Override
    public List<GetCarListResponse> getAllCars() {
        List<Car> cars = this.carRepository.findAll();
        List<GetCarListResponse> responses = cars.stream().map(car ->this.mapperService
                                .forResponse()
                                .map(car, GetCarListResponse.class))
                .collect(Collectors.toList());


        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        return null;
    }

    @Override
    public String saveCar(AddCarRequest addCarRequest) {
        return null;
    }

    @Override
    public String updateCar(UpdateCarRequest updateCarRequest) {
        return null;
    }

    @Override
    public String deleteCar(int id) {
        return null;
    }
}
