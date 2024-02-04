package com.example.rent2gojavaproject.services;

import com.example.rent2gojavaproject.models.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("support@rentogo.com.tr")
    private String fromEmail;

    public void sendEmail(ContactForm form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo("syln.soner@gmail.com");
        message.setSubject("Yeni İletişim Formu Gönderimi");
        message.setText(
                "Adı: " + form.getFirstName() + " " + form.getLastName() + "\n" +
                        "E-posta: " + form.getEmail() + "\n" +
                        "Telefon: " + form.getPhone() + "\n" +
                        "Mesaj: " + form.getMessage()
        );

        mailSender.send(message);
    }
}
