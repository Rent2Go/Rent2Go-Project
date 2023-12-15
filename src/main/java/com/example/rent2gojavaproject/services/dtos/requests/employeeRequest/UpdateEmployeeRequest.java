package com.example.rent2gojavaproject.services.dtos.requests.employeeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {


    private int id;

    private double salary;

    private int userId;
}
