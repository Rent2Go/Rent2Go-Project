package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;


    public void checkIfExistsByEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException(MessageConstants.EMAIL_ALREADY_EXISTS.getMessage());
        }

    }

    public void checkIfExistsByIdCarNumber(String idCardNumber) {


        if (userRepository.existsByIdCardNumber(idCardNumber)) {
            throw new AlreadyExistsException(MessageConstants.ID_CARD_NUMBER_ALREADY_EXISTS.getMessage());
        }

    }

    public void checkIfExistsByIdCarNumber(int id, String idCardNumber) {
        User user = this.userRepository.getReferenceById(id);

        if (!user.getIdCardNumber().equals(idCardNumber)) {

            if (userRepository.existsByIdCardNumber(idCardNumber)) {
                throw new AlreadyExistsException(MessageConstants.ID_CARD_NUMBER_ALREADY_EXISTS.getMessage());
            }
        }


    }

    public void checkIfExistsByEmail(int id, String email) {
        User user = this.userRepository.getReferenceById(id);
        if (!user.getEmail().equals(email)) {
            if (userRepository.existsByEmail(email)) {
                throw new AlreadyExistsException(MessageConstants.EMAIL_ALREADY_EXISTS.getMessage());
            }

        }


    }


    public void checkIfExistsPhoneNumber(String phoneNumber) {

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(MessageConstants.PHONE_NUMBER_ALREADY_EXISTS.getMessage());
        }

    }

    public void checkIfExistsPhoneNumber(int id, String phoneNumber) {
        User user = this.userRepository.getReferenceById(id);
        if (!user.getPhoneNumber().equals(phoneNumber)) {
            if (userRepository.existsByPhoneNumber(phoneNumber)) {
                throw new AlreadyExistsException(MessageConstants.PHONE_NUMBER_ALREADY_EXISTS.getMessage());
            }

        }


    }


}
