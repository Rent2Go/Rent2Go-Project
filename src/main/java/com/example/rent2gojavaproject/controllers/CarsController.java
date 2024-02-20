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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
@CrossOrigin
public class CarsController {

    private final CarService carService;

    @GetMapping()
    public DataResult<List<GetCarListResponse>> getAllCar() {

        return this.carService.getAllCars();
    }

    @GetMapping("/{id}")
    public DataResult<GetCarResponse> getById(@PathVariable int id) {

        return this.carService.getById(id);
    }


    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createCar(@RequestPart("addCarRequest") AddCarRequest addCarRequest, @RequestPart("file") MultipartFile file) throws IOException {

        return this.carService.addCar(addCarRequest,file);
    }
    @PostMapping("/imageupdate")
    public Result updateCarImage(@RequestParam("plate") String carPlate , @RequestParam("file") MultipartFile file) throws IOException {

        return  this.carService.updateCarImage(carPlate,file);
    }


    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateCar(@RequestBody @Valid UpdateCarRequest updateCarRequest) {

        return this.carService.updateCar(updateCarRequest);
    }
    @PatchMapping("/isactive/{id}")
    public Result updateCarIsActive(@PathVariable("id") int id,  @RequestParam boolean isActive) {

        return this.carService.updateCarIsActive(id,isActive);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteCar(@PathVariable int id) {

        return this.carService.deleteCar(id);
    }

    @GetMapping("/getallisactive")
    public DataResult<Iterable<GetCarListResponse>> findAll(@RequestParam("active") boolean isActive) {

        return this.carService.findAll(isActive);
    }


}
