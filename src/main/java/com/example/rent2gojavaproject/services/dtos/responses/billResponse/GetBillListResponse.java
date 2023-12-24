package com.example.rent2gojavaproject.services.dtos.responses.billResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBillListResponse {
    private int id;

    private String name;

    private LocalDate date;

    private double price;
    private int userId;
}
