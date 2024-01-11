package com.example.rent2gojavaproject.services.dtos.responses.employeeResponse;

import com.example.rent2gojavaproject.models.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeListResponse {
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
