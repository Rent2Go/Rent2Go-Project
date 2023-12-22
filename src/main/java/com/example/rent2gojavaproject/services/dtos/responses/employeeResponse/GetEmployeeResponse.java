package com.example.rent2gojavaproject.services.dtos.responses.employeeResponse;

import com.example.rent2gojavaproject.models.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeResponse {

    private int id;

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private City city;
    private String district;
    private String address;
    private double salary;
}
