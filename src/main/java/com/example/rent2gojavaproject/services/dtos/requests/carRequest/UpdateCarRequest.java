package com.example.rent2gojavaproject.services.dtos.requests.carRequest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    @NotNull(message = "The car id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @NotNull(message = "The kilometer field cannot be null.")
    @Min(value = 0, message = "Kilometer must be greater than or equal to 0.")
    private int kilometer;
    @NotNull(message = "The year field cannot be null.")
    @Min(value = 2005, message = "Year must be greater than or equal to 2005.")
    @Max(value = 2024, message = "Year must be less than or equal to the current year.")
    private int year;

    private double dailyPrice;

    private String plate;

    private int modelId;

    private int colorId;
}
