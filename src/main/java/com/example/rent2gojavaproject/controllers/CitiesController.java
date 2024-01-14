package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.CityService;
import com.example.rent2gojavaproject.services.dtos.responses.cityResponse.GetCityListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@AllArgsConstructor
public class CitiesController {

    private final CityService cityService;

    @GetMapping("/getall")
    public DataResult<List<GetCityListResponse>> getAllActiveCities() {
        return cityService.getAllCities();
    }
}
