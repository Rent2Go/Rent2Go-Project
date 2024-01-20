package com.example.rent2gojavaproject.registration;

import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.registration.token.ConfirmationToken;
import com.example.rent2gojavaproject.registration.token.ConfirmationTokenService;
import com.example.rent2gojavaproject.services.AuthenticationService;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;






    public String buildEmail(String name, String link) {
        return  "<html>" +
                "<body>" +
                "<div style='font-family: Arial, sans-serif;'>" +
                "<h1 style='color: #445566;'>Welcome to Rent2Go!</h1>" +
                "<p>Dear " +name + " ,</p>" +
                "<p>Thank you for registering with us. To complete your registration, please click the button below:</p>" +
                "<a href=\"" + link + "\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; margin: 10px 0; display: inline-block;'>Verify Your Email</a>" +
                "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                "<p><a href=\"" + link+ "\">" + link + "</a></p>" +
                "<p>Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!</p>" +
                "<p>Best Regards,<br>The Rent2Go Team</p>" +
                "</div>" +
                "</body>" +
                "</html>";


    }
}