package com.example.rent2gojavaproject.services.dtos.responses.rentalResponse;

import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomer;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerResponse;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeListResponse;
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

    private GetCarResponse car;

    private GetCustomer customer;

    private GetEmployeeListResponse employee;

    private GetDiscountListResponse discount;
}
