package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.repositories.BrandRepository;
import com.example.rent2gojavaproject.services.abstracts.BrandService;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.AddBrandRequest;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.UpdateBrandRequest;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;
import com.example.rent2gojavaproject.services.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private ModelMapperService mapperService;
    private BrandBusinessRules businessRules;

    @Override
    public List<GetBrandListResponse> getAllBrands() {
        return null;
    }

    @Override
    public GetBrandResponse getById(int id) {
        return null;
    }

    @Override
    public String addBrand(AddBrandRequest addBrandRequest) {
        return null;
    }

    @Override
    public String updateBrand(UpdateBrandRequest updateBrandRequest) {
        return null;
    }

    @Override
    public String deleteBrand(int id) {
        return null;
    }
}
