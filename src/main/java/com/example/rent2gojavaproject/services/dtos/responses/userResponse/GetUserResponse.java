package com.example.rent2gojavaproject.services.dtos.responses.userResponse;

import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String password;

    private String role;

    private boolean isActive;

    private String imageUrl;

    private GetCustomerResponse customer;
}
