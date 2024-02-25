package com.example.rent2gojavaproject.services.dtos.requests.rentalRequest;


import com.example.rent2gojavaproject.core.exceptions.FutureOrPresent;
import com.example.rent2gojavaproject.models.Discount;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @FutureOrPresent(message = "Start Date must be in the future !!")
    private LocalDate startDate;

    @NotNull(message = "End Date must be entered !!")
    @Future(message = "End Date must be in the future !!")
    private LocalDate endDate;

    @NotNull(message = "Car ID must be entered !!")
    private int carId;

    @NotNull(message = "Customer ID must be entered !!")
    private int customerId;

    private int employeeId;

    private Discount discount;



}
