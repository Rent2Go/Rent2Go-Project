package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.models.Customer;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import com.example.rent2gojavaproject.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerBusinessRules {


    private CustomerRepository customerRepository;

    public void checkIfExistsByUser(int userId) {

       Customer customer = this.customerRepository.getCustomerByUserId(userId);
        if (customer != null) {
            throw new AlreadyExistsException( "There is "+  MessageConstants.ALREADY_EXISTS.getMessage() + " a customer for the selected user" );
        }


    }


}