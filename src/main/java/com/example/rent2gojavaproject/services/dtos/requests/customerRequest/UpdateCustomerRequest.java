package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import com.example.rent2gojavaproject.models.City;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    @NotNull(message = "The customer id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;

    @NotBlank(message = "Nationality ID cannot be blank")
    @Pattern(regexp = "[0-9]{11}", message = "Nationality ID must be 11 digits numeric")
    private String nationalityId;
    @NotNull(message = "The user id cannot be null.")
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
