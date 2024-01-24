package com.example.rent2gojavaproject.services.abstracts;

import jakarta.servlet.http.HttpServletRequest;

public interface EmailSenderService {


    void send(String to, String email);

    public String applicationUrl(HttpServletRequest request);

    String buildEmail(String name, String link) ;

    String sendResetPasswordEmail(String name,String url);
}
