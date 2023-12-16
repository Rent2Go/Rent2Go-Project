package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.repositories.ColorRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ColorBusinessRules {


    private ColorRepository colorRepository;

    public void checkIfExistsByName(String name){
        if(colorRepository.existsByName(name.toLowerCase().trim())){
            throw new IllegalArgumentException("Color already exists");
        }
    }


}