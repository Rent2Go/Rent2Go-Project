package com.example.rent2gojavaproject.services.dtos.requests.rentalRequest;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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


    @NotNull(message = "Car ID must be entered !!")
    private int carId;

    @NotNull(message = "Customer ID must be entered !!")
    private int customerId;

    @NotNull(message = "Employee ID must be entered !!")
    private int employeeId;

    @NotNull(message = "Discount ID must be entered !!")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Discount code must consist of uppercase letters and numbers only.")
    private String discountCode;


}
