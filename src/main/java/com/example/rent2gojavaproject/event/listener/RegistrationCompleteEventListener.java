package com.example.rent2gojavaproject.event.listener;

import com.example.rent2gojavaproject.event.RegistrationCompleteEvent;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener
        implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;
    private final JavaMailSender mailSender;
    private User theUser;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        theUser = event.getUser();
        // 2. Generate a verification token for user
        String verificationToken = UUID.randomUUID().toString();
        // 3. Send the verification token to the user via email
        userService.saveUserVerificationToken(theUser, verificationToken);
        //4. Build the verification url to send to the user
        String url = event.getApplicationUrl() + "/api/verifyEmail?token=" + verificationToken;
        //5. Send the verification url to the user via email
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click on the link below to verify your email address: {}", url);

    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "Rent2Go Company";

        String htmlContent =
                "<html>" +
                        "<body>" +
                        "<div style='font-family: Arial, sans-serif;'>" +
                        "<h1 style='color: #445566;'>Welcome to Rent2Go!</h1>" +
                        "<p>Dear " + theUser.getName() + " " + theUser.getSurname() + ",</p>" +
                        "<p>Thank you for registering with us. To complete your registration, please click the button below:</p>" +
                        "<a href=\"" + url + "\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; margin: 10px 0; display: inline-block;'>Verify Your Email</a>" +
                        "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                        "<p><a href=\"" + url + "\">" + url + "</a></p>" +
                        "<p>Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!</p>" +
                        "<p>Best Regards,<br>The Rent2Go Team</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>";

        String textContent =
                "Dear " + theUser.getName() + " " + theUser.getSurname() + ",\n" +
                        "Thank you for registering with us. To complete your registration, please visit the following URL:\n" +
                        url + "\n" +
                        "Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!\n" +
                        "Best Regards,\nThe Rent2Go Team";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        messageHelper.setFrom("support@rentogo.com.tr", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(textContent, htmlContent);

        mailSender.send(message);
    }
}
