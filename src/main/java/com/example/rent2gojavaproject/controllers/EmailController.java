package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest.ContactFormRequest;
import com.example.rent2gojavaproject.services.dtos.requests.reservationDetailRequest.AddReservationDetailRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class EmailController {
    private final EmailSenderService emailService;

    @PostMapping("/send-contact-email")
    @ResponseStatus(code = HttpStatus.OK)
    public void sendEmail(@RequestBody ContactFormRequest contactForm) throws MessagingException, UnsupportedEncodingException {
        emailService.sendContactEmail(contactForm);
        emailService.sendThankYouEmail(contactForm);
    }

    @PostMapping("/reservation-details")
    @ResponseStatus(code = HttpStatus.OK)
    public void sendReservationDetailEmail(@RequestBody AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException {
        emailService.reservationDetailEmail(form);
    }

    @PostMapping("/send-cash-email")
    @ResponseStatus(code = HttpStatus.OK)
    public void sendCashEmail(@RequestBody AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException {
        emailService.cashReservationDetail(form);
    }
}
