package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.BrandService;
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
@CrossOrigin
public class BrandsController {

    private final BrandService brandService;

    @GetMapping("/getall")
    public DataResult<List<GetBrandListResponse>> getAllActiveBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/getAllActiveOrNot")
    public DataResult<Iterable<GetBrandListResponse>> findAll(@RequestParam boolean isActive) {
        return this.brandService.findAll(isActive);
    }

    @GetMapping("/{id}")

    public DataResult<GetBrandResponse> getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Result createBrand(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        return brandService.addBrand(addBrandRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Result updateBrand(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return brandService.updateBrand(updateBrandRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteBrand(@PathVariable int id) {
        return brandService.deleteBrand(id);
    }
}

