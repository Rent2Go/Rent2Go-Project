package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Brand;
import com.example.rent2gojavaproject.models.Color;
import com.example.rent2gojavaproject.repositories.BrandRepository;
import com.example.rent2gojavaproject.services.abstracts.BrandService;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.AddBrandRequest;
import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.UpdateBrandRequest;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;
import com.example.rent2gojavaproject.services.rules.BrandBusinessRules;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private ModelMapperService mapperService;
    private BrandBusinessRules businessRules;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetBrandListResponse>> getAllBrands() {

        List<Brand> brands = this.brandRepository.findAll();
        List<GetBrandListResponse> responses = brands.stream().map(brand -> this.mapperService.forResponse().map(brand, GetBrandListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetBrandListResponse>> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("isActiveFilterBrand");
        filter.setParameter("isActive", isDeleted);
        Iterable<GetBrandListResponse> brands = this.brandRepository.findAll()
                .stream().map(brand -> this.mapperService.forResponse()
                        .map(brand, GetBrandListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter("isActiveFilterBrand");
        return new SuccessDataResult<>(brands,Message.GET_ALL.getMessage());
    }


    @Override
    public DataResult<GetBrandResponse> getById(int id) {

        Brand brand = this.brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find brand id"));

        GetBrandResponse response = this.mapperService.forResponse().map(brand, GetBrandResponse.class);
        return new SuccessDataResult<>(response, Message.GET.getMessage());
    }

    @Override
    public Result addBrand(AddBrandRequest addBrandRequest) {
        String editName = businessRules.checkIfExistsByName(addBrandRequest.getName());
        Brand brand = this.mapperService.forRequest().map(addBrandRequest, Brand.class);
        brand.setName(editName);
        this.brandRepository.save(brand);
        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateBrand(UpdateBrandRequest updateBrandRequest) {
        String editName = businessRules.checkIfExistsByName(updateBrandRequest.getName());
        this.brandRepository.findById(updateBrandRequest.getId()).orElseThrow(() -> new RuntimeException("Brand not found !"));
        Brand brand = this.mapperService.forRequest().map(updateBrandRequest, Brand.class);
        brand.setName(editName);
        this.brandRepository.save(brand);

        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteBrand(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        brand.setDeletedAt(LocalDate.now());
        this.brandRepository.save(brand);
        this.brandRepository.delete(brand);

        return new SuccessResult(Message.DELETE.getMessage());
    }

    @Override
    public boolean existsById(int id) {
        return this.brandRepository.existsById(id);
    }
}
