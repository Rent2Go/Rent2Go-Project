package com.example.rent2gojavaproject.services.dtos.requests.employeeRequest;


import com.example.rent2gojavaproject.models.City;
import jakarta.persistence.Column;
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
public class AddEmployeeRequest {

    @Positive(message = "Salary must be a positive number.")
    @NotNull(message = "Salary cannot be null")
    private double salary;
    private City city;
    @NotBlank(message = "District field cannot be blank")
    @Size(max = 100, message = "District field cannot exceed 100 characters")
    private String district;
    @NotBlank(message = "Address field cannot be blank")
    @Size(max = 300, message = "Address field cannot exceed 300 characters")
    private String address;

}
