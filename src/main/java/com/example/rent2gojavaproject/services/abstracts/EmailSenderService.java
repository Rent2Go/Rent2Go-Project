package com.example.rent2gojavaproject.services.abstracts;

public interface EmailSenderService {


    void send(String to, String email);

     String buildEmail(String name, String link) ;
}
