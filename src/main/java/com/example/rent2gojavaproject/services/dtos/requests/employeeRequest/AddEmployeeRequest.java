package com.example.rent2gojavaproject.services.dtos.requests.employeeRequest;


import com.example.rent2gojavaproject.models.JobTitle;
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
    @NotNull
    private int jobTitleId;

}
