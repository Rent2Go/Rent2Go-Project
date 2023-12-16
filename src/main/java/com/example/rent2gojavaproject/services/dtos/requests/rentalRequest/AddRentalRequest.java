package com.example.rent2gojavaproject.services.dtos.requests.rentalRequest;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddRentalRequest {
    

    @NotNull(message = "Start Date must be entered !!")
    @Future(message = "Start Date must be in the future !!")
    private LocalDate startDate;

    @NotNull(message = "End Date must be entered !!")
    @Future(message = "End Date must be in the future !!")
    private LocalDate endDate;

    private LocalDate returnDate;

    @NotNull(message = "Start Kilometer must be entered !!")
    @Positive(message = "Start Kilometer must be bigger than 0 !!")
    private int startKilometer;

    @NotNull(message = "Car ID must be entered !!")
    private int carId;

    @NotNull(message = "Customer ID must be entered !!")
    private int customerId;

    @NotNull(message = "Employee ID must be entered !!")
    private int employeeId;

    @NotNull(message = "Discount ID must be entered !!")
    private int discountId;


}
