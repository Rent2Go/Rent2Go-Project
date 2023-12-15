package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import com.example.rent2gojavaproject.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;

    private ModelMapperService mapperService;

    private CarBusinessRules businessRules;


    @Override
    public List<GetCarListResponse> getAllCars() {
        List<Car> cars = this.carRepository.findAll();
        List<GetCarListResponse> responses = cars.stream().map(car -> this.mapperService
                        .forResponse()
                        .map(car, GetCarListResponse.class))
                .collect(Collectors.toList());


        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = this.carRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find car id"));

        GetCarResponse response = this.mapperService.forResponse().map(car, GetCarResponse.class);


        return response;
    }

    @Override
    public String createCar(AddCarRequest addCarRequest) {

        String editPlate = this.businessRules.plateUniqueness(addCarRequest.getPlate());
        addCarRequest.setPlate(editPlate);
        this.businessRules.updateCarMethod(addCarRequest.getModelId(), addCarRequest.getColorId());

        Car car = this.mapperService.forRequest().map(addCarRequest, Car.class);

        this.carRepository.save(car);

        return "Transaction Successfully";

    }

    @Override
    public String updateCar(UpdateCarRequest updateCarRequest) {

        String editPlate = this.businessRules.plateUniqueness(updateCarRequest.getPlate());
        updateCarRequest.setPlate(editPlate);
        this.businessRules.updateCarMethod(updateCarRequest.getModelId(), updateCarRequest.getColorId());
        Car car = this.carRepository.findById(updateCarRequest.getId()).orElseThrow(() -> new RuntimeException("Car not found"));

        car = this.mapperService.forRequest().map(updateCarRequest, Car.class);

        this.carRepository.save(car);


        return "Transaction Successfully";
    }

    @Override
    public String deleteCar(int id) {
        this.carRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        this.carRepository.deleteById(id);

        return "Transaction Successfully";
    }
}
