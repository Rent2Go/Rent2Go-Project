package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    private String nationalityId;

    private int userId;
}
