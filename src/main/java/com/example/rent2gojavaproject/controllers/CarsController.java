package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    public String createCar(@RequestBody @Valid AddCarRequest addCarRequest) {

        return  this.carService.createCar(addCarRequest);
    }

}
