package com.example.rent2gojavaproject.services.dtos.responses.rentalResponse;

import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRentalListResponse {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private int startKilometer;

    private Integer endKilometer;

    private double totalPrice;

    private GetCarListResponse car;

    private int customerId;

    private int employeeId;

    private double discountPercentage;
}
