package com.example.rent2gojavaproject.services.dtos.requests.discountRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDiscountRequest {
    @NotNull(message = "Discount Code can not be empty !")
    private String discountCode;
    @NotNull(message = "Percentage can not be empty !")
    @Positive(message = "Please enter a positive value !!")
    private double percentage;
    @NotNull(message = "Rental Id can not be empty !")
    private int rentalId;


}
