package com.example.rent2gojavaproject.services.dtos.requests.modelRequest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {

    @NotBlank(message = "The model name can't be empty.")
    @NotNull(message = "The model name cannot be null.")
    @Size(min=2, max = 15, message = "The Model Name must be between 2 and 15 characters !")
    private String name;

    @NotNull(message = "The brand id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int brandId;
}

