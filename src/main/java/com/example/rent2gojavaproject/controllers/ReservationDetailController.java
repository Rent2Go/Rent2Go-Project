package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.concretes.EmailManager;
import com.example.rent2gojavaproject.services.dtos.requests.reservationDetailRequest.AddReservationDetailRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/reservation-details")
@AllArgsConstructor
@CrossOrigin
public class ReservationDetailController {
    private final EmailManager emailService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public void sendReservationDetailEmail(@RequestBody AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException {
        emailService.reservationDetailEmail(form);
    }
}
