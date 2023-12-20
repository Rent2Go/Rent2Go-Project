package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.models.Brand;
import com.example.rent2gojavaproject.repositories.BrandRepository;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public String checkIfExistsByName(String name){
        String value = name.toLowerCase().trim();
        if(brandRepository.existsByNameAndIsActiveTrue(value)){
            throw new IllegalArgumentException("Brand already exists");
        }
        return value;
    }



}
