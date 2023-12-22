package com.example.rent2gojavaproject.services.dtos.requests.employeeRequest;

import com.example.rent2gojavaproject.models.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "District field cannot be blank")
    @Size(max = 100, message = "District field cannot exceed 100 characters")
    private String district;
    @NotBlank(message = "Address field cannot be blank")
    @Size(max = 300, message = "Address field cannot exceed 300 characters")
    private String address;
    private City city;
    private boolean isActive;
}
