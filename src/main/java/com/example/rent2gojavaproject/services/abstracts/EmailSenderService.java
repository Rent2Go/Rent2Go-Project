package com.example.rent2gojavaproject.services.abstracts;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface EmailSenderService {


    void sendEmail(String name, String email, String link, String subject, String htmlContent, String textContent) throws MessagingException, UnsupportedEncodingException;

    public String applicationUrl(HttpServletRequest request);

    void buildEmail(String name,String email, String link) throws MessagingException, UnsupportedEncodingException;

    void sendResetPasswordEmail(String name,String email, String url) throws MessagingException, UnsupportedEncodingException;
}
