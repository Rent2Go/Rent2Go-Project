package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.repositories.BrandRepository;
import com.example.rent2gojavaproject.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {

    private ModelRepository modelRepository;
    private BrandRepository brandRepository;

    public String checkIfExistsByIdAndName(int brandId, String name){
        String value = name.toLowerCase().trim();
        if (!(brandRepository.existsById(brandId))) {
            throw new IllegalStateException("Brand ID doesn't exist !");
        }else if(modelRepository.existsByName(value)){
            throw new IllegalArgumentException("Model already exists");
        }
        return value;
    }


}
