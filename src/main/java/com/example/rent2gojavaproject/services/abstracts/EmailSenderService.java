package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest.ContactFormRequest;
import com.example.rent2gojavaproject.services.dtos.requests.reservationDetailRequest.AddReservationDetailRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.UnsupportedEncodingException;

public interface EmailSenderService {


    JavaMailSender getJavaMailSender();

    void sendEmail(String name, String email, String link, String subject, String htmlContent, String textContent) throws MessagingException, UnsupportedEncodingException;

    String applicationUrl(HttpServletRequest request);

    void buildEmail(String name,String email, String link) throws MessagingException, UnsupportedEncodingException;

    void sendResetPasswordEmail(String name,String email, String url) throws MessagingException, UnsupportedEncodingException;

    void sendContactEmail(ContactFormRequest form) throws MessagingException, UnsupportedEncodingException;

    void sendThankYouEmail(ContactFormRequest form) throws MessagingException, UnsupportedEncodingException;

    void reservationDetailEmail(AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException;

    void cashReservationDetail(AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException;
}
