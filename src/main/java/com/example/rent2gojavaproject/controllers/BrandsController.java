package com.example.rent2gojavaproject.controllers;

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
    public List<GetBrandListResponse> getAllBrands() {
        return brandManager.getAllBrands();
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable int id) {
        return brandManager.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBrand(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        return brandManager.addBrand(addBrandRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public String updateBrand(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return brandManager.updateBrand(updateBrandRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBrand(@PathVariable int id) {
        return brandManager.deleteBrand(id);
    }
}

