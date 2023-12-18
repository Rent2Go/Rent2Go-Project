package com.example.rent2gojavaproject.services.dtos.requests.employeeRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {

    @NotNull(message = "The employee id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @Positive(message = "Salary must be a positive number.")
    @NotNull(message = "Salary cannot be null")
    private double salary;
    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Id must be a positive number.")
    private int userId;
}
