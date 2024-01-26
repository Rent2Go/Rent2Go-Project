package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ColorBusinessRules {


    private ColorRepository colorRepository;

    public String checkIfExistsByName(String name) {

        String value = name.toLowerCase().trim();
        if (colorRepository.existsByName(value)) {
            throw new AlreadyExistsException(name + MessageConstants.ALREADY_EXISTS.getMessage());
        }

        return value;
    }


}