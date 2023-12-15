package com.example.rent2gojavaproject.services.dtos.responses.customerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerListResponse {
    private int id;
    private String nationalityId;
    private String username;
}
