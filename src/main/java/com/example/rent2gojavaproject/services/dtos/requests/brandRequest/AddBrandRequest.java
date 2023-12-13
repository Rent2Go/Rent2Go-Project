package com.example.rent2gojavaproject.services.dtos.requests.brandRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBrandRequest {
    @NotNull(message = "The brand name cannot be null.")
    @NotBlank(message = "The brand name can't be empty.")
    @Size(min = 3, max = 15, message = "Brand name should be between 3 and 15 digits.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The brand name should only contain letters.")
    private String name;
}
