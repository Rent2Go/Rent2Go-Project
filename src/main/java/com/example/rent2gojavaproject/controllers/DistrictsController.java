package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.DistrictService;
import com.example.rent2gojavaproject.services.dtos.responses.districtResponse.GetDistrictListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/districts")
@CrossOrigin
public class DistrictsController {

    private final DistrictService districtService;

    @GetMapping()
    public DataResult<List<GetDistrictListResponse>> getAllActiveDistricts() {
        return districtService.getAllDistricts();
    }
}
