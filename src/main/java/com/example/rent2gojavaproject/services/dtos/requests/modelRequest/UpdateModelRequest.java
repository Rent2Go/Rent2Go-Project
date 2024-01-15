package com.example.rent2gojavaproject.services.dtos.requests.modelRequest;

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
public class UpdateModelRequest {

    @NotNull(message = "The model id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;

    @NotBlank(message = "The model name can't be empty.")
    @NotNull(message = "The model name cannot be null.")
    @Size(min = 2, max = 15, message = "The Model Name must be between 2 and 15 characters !")
    private String name;

    @NotNull(message = "The brand id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int brandId;

    private boolean isActive;
}
