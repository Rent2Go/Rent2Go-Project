package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.RentalService;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/rentals")
@AllArgsConstructor
public class RentalsController {

    private final RentalService rentalService;

    @GetMapping("/getall")
    public DataResult<List<GetRentalListResponse>> getAllRentals() {
        return rentalService.getAllRentals();
    }
    @GetMapping("/getAllActiveOrNot")
    public DataResult<Iterable<GetRentalListResponse>> findAll(@RequestParam boolean isActive) {
        return this.rentalService.findAll(isActive);
    }
    @GetMapping("/{id}")
    public DataResult<GetRentalResponse> getRentalById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createRental(@RequestBody @Valid AddRentalRequest addRentalRequest) {
        return rentalService.addRental(addRentalRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateRental(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        return rentalService.updateRental(updateRentalRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteRental(@PathVariable int id) {
        return rentalService.deleteRental(id);
    }
}

