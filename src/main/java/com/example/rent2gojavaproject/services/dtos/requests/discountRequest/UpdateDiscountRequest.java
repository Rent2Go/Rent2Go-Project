package com.example.rent2gojavaproject.services.dtos.requests.discountRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiscountRequest {
    private int id;
    private String discountCode;
    private double percentage;
    private int rentalId;
}
