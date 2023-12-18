package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.AddBrandRequest;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.UpdateBrandRequest;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;

import java.util.List;

public interface BrandService {
    DataResult<List<GetBrandListResponse>> getAllBrands();

    DataResult<GetBrandResponse> getById(int id);

    Result addBrand(AddBrandRequest addBrandRequest);

    Result updateBrand(UpdateBrandRequest updateBrandRequest);

    Result deleteBrand(int id);

    boolean existsById(int id);
}
