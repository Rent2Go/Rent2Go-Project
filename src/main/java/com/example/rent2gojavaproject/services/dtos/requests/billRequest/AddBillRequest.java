package com.example.rent2gojavaproject.services.dtos.requests.billRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBillRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @Positive(message = "Price should be a positive number")
    private double price;

    @Positive(message = "UserID should be a positive integer")
    private int userId;
}
