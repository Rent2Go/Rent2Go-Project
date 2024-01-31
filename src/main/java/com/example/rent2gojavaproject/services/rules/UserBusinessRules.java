package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;


    public void checkIfExistsByEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException( MessageConstants.EMAIL_ALREADY_EXISTS.getMessage());
        }

    }

    public void checkIfExistsPhoneNumber(String phoneNumber) {

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException( MessageConstants.PHONE_NUMBER_ALREADY_EXISTS.getMessage());
        }

    }


}
