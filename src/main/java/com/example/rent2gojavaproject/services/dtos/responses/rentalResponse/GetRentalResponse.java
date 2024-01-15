package com.example.rent2gojavaproject.services.dtos.responses.rentalResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRentalResponse {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private int startKilometer;

    private Integer endKilometer;

    private double totalPrice;

    private int carId;

    private int customerId;

    private int employeeId;

    private double discountPercentage;
}
