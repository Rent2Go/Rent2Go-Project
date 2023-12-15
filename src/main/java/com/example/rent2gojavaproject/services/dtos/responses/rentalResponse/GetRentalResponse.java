package com.example.rent2gojavaproject.services.dtos.responses.rentalResponse;

import java.time.LocalDate;

public class GetRentalResponse {
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private int startKilometer;

    private int endKilometer;

    private double totalPrice;

    private int carId;

    private int customerId;

    private int employeeId;

    private int discountId;
}
