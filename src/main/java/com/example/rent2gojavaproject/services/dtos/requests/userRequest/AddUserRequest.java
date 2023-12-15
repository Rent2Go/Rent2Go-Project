package com.example.rent2gojavaproject.services.dtos.requests.userRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;

    private String surname;

    private String phoneNumber;

    private String email;
}
