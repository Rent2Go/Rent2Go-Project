package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.repositories.BrandRepository;
import com.example.rent2gojavaproject.repositories.ModelRepository;
import com.example.rent2gojavaproject.services.abstracts.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {

    private ModelRepository modelRepository;
    private BrandService brandService;

    public String checkIfExistsByIdAndName(int brandId, String name){
        String value = name.toLowerCase().trim();
        if (!(brandService.existsById(brandId))) {
            throw new NotFoundException("Brand ID doesn't exist !");
        }else if(modelRepository.existsByName(value)){
            throw new AlreadyExistsException("Model already exists");
        }
        return value;
    }


}
