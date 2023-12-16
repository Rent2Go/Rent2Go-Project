package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.concretes.BrandManager;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.AddBrandRequest;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.UpdateBrandRequest;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {

    private final BrandManager brandManager;

    @GetMapping("/getall")
    public DataResult<List<GetBrandListResponse>> getAllBrands() {
        return brandManager.getAllBrands();
    }

    @GetMapping("/{id}")
    public DataResult<GetBrandResponse> getById(@PathVariable int id) {
        return brandManager.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Result createBrand(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        return brandManager.addBrand(addBrandRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Result updateBrand(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return brandManager.updateBrand(updateBrandRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteBrand(@PathVariable int id) {
        return brandManager.deleteBrand(id);
    }
}

