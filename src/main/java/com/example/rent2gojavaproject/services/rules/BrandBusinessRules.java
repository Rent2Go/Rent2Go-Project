package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.models.Brand;
import com.example.rent2gojavaproject.models.Model;
import com.example.rent2gojavaproject.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public String checkIfExistsByName(String name){
        String value = name.toLowerCase().trim();
        if(brandRepository.existsByNameAndIsActiveTrue(value)){
            throw new AlreadyExistsException("Brand name already exists");
        }
        return value;
    }

    public void changeIsActive(Brand brand, boolean isActive) {
        brand.setActive(isActive);

        List<Model> existingModels = brand.getModels();
        if (existingModels != null) {
            existingModels.forEach(model -> {
                model.setActive(brand.isActive());
                model.getCars().forEach(car -> car.setActive(model.isActive()));

            });
        }

    }



}
