package com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactFormRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String message;


    @Override
    public String toString() {
        return "ContactForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
