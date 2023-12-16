package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private int id;

    private String nationalityId;

    private int userId;
}
