package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.models.ContactForm;
import com.example.rent2gojavaproject.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ContactController {

    private final EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody ContactForm contactForm) {
        emailService.sendEmail(contactForm);

        if (emailService != null) {
            emailService.sendEmail(contactForm);
        } else {
            throw new NullPointerException("Email service is null");
        }
    }
}
