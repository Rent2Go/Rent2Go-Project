package com.example.rent2gojavaproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String message;

    // Getter ve setter metodlarını ekleyin

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
