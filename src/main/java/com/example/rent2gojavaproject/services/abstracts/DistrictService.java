package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.models.District;
import com.example.rent2gojavaproject.services.dtos.responses.districtResponse.GetDistrictListResponse;

import java.util.List;

public interface DistrictService {
    DataResult<List<GetDistrictListResponse>> getAllDistricts();


    DataResult<GetDistrictListResponse> getById(int id);
    District getByIdDist(int id);
}
