package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.dtos.responses.cityResponse.GetCityListResponse;

import java.util.List;

public interface CityService {
    DataResult<List<GetCityListResponse>> getAllCities();
}
