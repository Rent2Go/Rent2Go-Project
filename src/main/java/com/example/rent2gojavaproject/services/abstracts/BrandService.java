package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.AddBrandRequest;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.UpdateBrandRequest;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetBrandListResponse> getAllBrands();

    GetBrandResponse getById(int id);

    String addBrand(AddBrandRequest addBrandRequest);

    String updateBrand(UpdateBrandRequest updateBrandRequest);

    String deleteBrand(int id);
}
