package com.example.rent2gojavaproject.services.dtos.requests.colorRequest;

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
public class AddColorRequest {
    @NotNull(message = "The color name cannot be null.")
    @NotBlank(message = "The color name can't be empty.")
    @Size(min = 2, max = 15, message = "Color name should be between 2 and 15 digits.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The color name should only contain letters.")
    private String name;
    private String hexCode;
}
