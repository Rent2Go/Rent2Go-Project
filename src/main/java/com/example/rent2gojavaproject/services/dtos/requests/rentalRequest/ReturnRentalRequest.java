package com.example.rent2gojavaproject.services.dtos.requests.rentalRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnRentalRequest {

    @NotNull(message = "Rental ID must be entered !!")
    @Positive(message = "Rental ID must be bigger than 0 !!")
    private int id;


    private LocalDate returnDate;

    @Positive(message = "End Kilometer must be bigger than 0 !!")
    private Integer endKilometer;

    @NotNull
    @Positive(message = "Car Id must be greater than 0 !!")

    private int carId;


}
