package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    @NotBlank(message = "Nationality ID cannot be blank")
    @Pattern(regexp = "[0-9]{11}", message = "Nationality ID must be 11 digits numeric")
    private String nationalityId;
    @NotNull(message = "The user id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int userId;
}
