package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerDriverLicence {

    @NotNull(message = "The customer id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @NotNull(message = "The Driver issue date cannot be null.")
    @Past(message = "Issue date must be in the past")
    private LocalDate issueDate;
    @NotNull(message = "The expiry date cannot be null.")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;



}
