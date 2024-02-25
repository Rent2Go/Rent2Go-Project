package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    @NotNull(message = "The user id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int userId;



    @Past(message = "Expiry date must be in the past")
    private LocalDate issueDate;

    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;


}
