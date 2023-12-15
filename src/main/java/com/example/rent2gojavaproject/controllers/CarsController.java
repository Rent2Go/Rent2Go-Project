package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/cars")
@AllArgsConstructor
public class CarsController {

    private final CarService carService;

    @GetMapping("/getall")
    public List<GetCarListResponse> getAllCar() {

        return this.carService.getAllCars();
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id) {

        return this.carService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createCar(@RequestBody @Valid AddCarRequest addCarRequest) {

        return  this.carService.addCar(addCarRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateCar(@RequestBody @Valid  UpdateCarRequest updateCarRequest){

        return  this.carService.updateCar(updateCarRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteCar(@PathVariable  int id) {

        return this.carService.deleteCar(id);
    }



}
