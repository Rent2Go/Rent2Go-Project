package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.models.Brand;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private ModelMapperService mapperService;
    private BrandBusinessRules businessRules;

    @Override
    public List<GetBrandListResponse> getAllBrands() {
        List<Brand> brands = this.brandRepository.findAll();
        List<GetBrandListResponse> responses = brands.stream().map(brand -> this.mapperService.forResponse().map(brand, GetBrandListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find brand id"));

        GetBrandResponse response = this.mapperService.forResponse().map(brand, GetBrandResponse.class);
        return response;
    }

    @Override
    public String addBrand(AddBrandRequest addBrandRequest) {
        Brand brand = this.mapperService.forRequest().map(addBrandRequest, Brand.class);
        this.brandRepository.save(brand);
        return "Transaction Successfully.";
    }

    @Override
    public String updateBrand(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.brandRepository.findById(updateBrandRequest.getId()).orElseThrow(() -> new RuntimeException("Brand not found !"));

        brand = this.mapperService.forRequest().map(updateBrandRequest, Brand.class);

        this.brandRepository.save(brand);

        return "Transaction Successfully.";
    }

    @Override
    public String deleteBrand(int id) {
        return null;
    }
}
