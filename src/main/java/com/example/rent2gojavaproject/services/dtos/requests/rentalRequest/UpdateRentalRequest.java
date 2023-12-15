package com.example.rent2gojavaproject.services.dtos.requests.rentalRequest;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class UpdateRentalRequest {
    @NotNull(message = "Start Date is required !!")
    @Future(message = "Start Date must be in the future !!")
    private LocalDate startDate;

    @NotNull(message = "End Date is required !!")
    @Future(message = "End Date must be in the future !!")
    private LocalDate endDate;

    private LocalDate returnDate;

    @NotNull(message = "Start Kilometer is required !!")
    @Positive(message = "Start Kilometer must be bigger than 0 !!")
    private int startKilometer;

    @Positive(message = "End Kilometer must be bigger than 0 !!")
    private int endKilometer;

    @Positive(message = "Total Price must be bigger than 0 !!")
    private double totalPrice;

    @NotNull(message = "Car ID is required !!")
    private int carId;

    @NotNull(message = "Customer ID is required !!")
    private int customerId;

    @NotNull(message = "Employee ID is required !!")
    private int employeeId;

    @NotNull(message = "Discount ID is required !!")
    private int discountId;

}
