package com.example.rent2gojavaproject.services.dtos.requests.reservationDetailRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddReservationDetailRequest {

    private String name;
    private String email;
    private String phone;
    private LocalDate startDate;
    private LocalDate endDate;
    private String totalDay;
    private String plate;
    private String carInfo;
    private String totalPrice;


}
