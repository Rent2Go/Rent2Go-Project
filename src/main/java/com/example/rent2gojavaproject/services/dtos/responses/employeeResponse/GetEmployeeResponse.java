package com.example.rent2gojavaproject.services.dtos.responses.employeeResponse;

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

    private String city;

    private String district;

    private String address;

    private double salary;
}
