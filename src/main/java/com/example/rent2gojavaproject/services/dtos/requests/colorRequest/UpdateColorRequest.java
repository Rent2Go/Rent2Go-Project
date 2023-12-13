package com.example.rent2gojavaproject.services.dtos.requests.colorRequest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
    @NotNull(message = "The color id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @NotNull(message = "The color name cannot be null.")
    @NotBlank(message = "The color name can't be empty.")
    @Size(min = 3, max = 15, message = "Color name should be between 3 and 15 digits.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The color name should only contain letters.")
    private String name;
}
