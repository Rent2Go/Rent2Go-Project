package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
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
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {

    private final CarService carService;

    @GetMapping("/getall")
    public DataResult<List<GetCarListResponse>> getAllCar() {

        return this.carService.getAllCars();
    }

    @GetMapping("/{id}")
    public DataResult<GetCarResponse> getById(@PathVariable int id) {

        return this.carService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createCar(@RequestBody @Valid AddCarRequest addCarRequest) {

        return this.carService.addCar(addCarRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateCar(@RequestBody @Valid UpdateCarRequest updateCarRequest) {

        return this.carService.updateCar(updateCarRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteCar(@PathVariable int id) {

        return this.carService.deleteCar(id);
    }

    @GetMapping("/getallsoftdelete")
    public DataResult<Iterable<GetCarListResponse>> findAll(@RequestParam boolean isActive) {

        return this.carService.findAll(isActive);
    }


}
