package com.example.rent2gojavaproject.services.dtos.requests.rentalRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalRequest {

    @NotNull(message = "Rental ID must be entered !!")
    @Positive(message = "Rental ID must be bigger than 0 !!")
    private int id;
    @NotNull(message = "Start Date must be entered !!")
    private LocalDate startDate;
    @NotNull(message = "End Date must be entered !!")
    private LocalDate endDate;
    private LocalDate returnDate;

    @Positive(message = "End Kilometer must be bigger than 0 !!")
    private Integer endKilometer;


    @NotNull(message = "Car ID must be entered !!")
    private int carId;

    @NotNull(message = "Customer ID must be entered !!")
    private int customerId;

    @NotNull(message = "Employee ID must be entered !!")
    private int employeeId;

}
