package com.example.rent2gojavaproject.services.dtos.requests.discountRequest;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiscountRequest {
    @NotNull(message = "Discount ID must be entered !!")
    @Positive(message = "Discount ID must be bigger than 0 !!")
    private int id;
    @NotNull(message = "Discount Code can not be empty !")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Discount code must consist of uppercase letters and numbers only.")
    private String discountCode;
    @NotNull(message = "Percentage can not be empty !")
    @Positive(message = "Please enter a positive value !!")
    @DecimalMax(value = "0.99", message = "Please enter a value less than or equal to 0.99")
    private Double percentage;
    @NotNull(message = "Rental Id can not be empty !")
    private int rentalId;
    private boolean isActive;
}
