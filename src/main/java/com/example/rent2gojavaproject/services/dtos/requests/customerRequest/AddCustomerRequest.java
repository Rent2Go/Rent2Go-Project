package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    @NotNull(message = "The user id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int userId;


}
