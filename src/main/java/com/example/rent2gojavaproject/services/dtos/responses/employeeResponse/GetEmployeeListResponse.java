package com.example.rent2gojavaproject.services.dtos.responses.employeeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeListResponse {
    private int id;

    private double salary;

    private String userName;
}
