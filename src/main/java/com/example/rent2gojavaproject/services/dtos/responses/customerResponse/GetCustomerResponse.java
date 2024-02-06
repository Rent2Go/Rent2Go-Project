package com.example.rent2gojavaproject.services.dtos.responses.customerResponse;

import com.example.rent2gojavaproject.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse {

    private int id;

    private String nationalityId;

    private String city;

    private String district;

    private String address;

    private User user;

}
