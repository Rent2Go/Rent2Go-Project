package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.concretes.EmailManager;
import com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest.ContactFormRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class ContactController {

    private final EmailManager emailService;

    @PostMapping("/send-contact-email")
    public void sendEmail(@RequestBody ContactFormRequest contactForm) throws MessagingException, UnsupportedEncodingException {
        emailService.sendContactEmail(contactForm);
        emailService.sendThankYouEmail(contactForm);
    }


}
