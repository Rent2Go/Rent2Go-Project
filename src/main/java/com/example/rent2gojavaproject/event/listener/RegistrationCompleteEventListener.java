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
                        "<body style='font-family: \"Roboto\", sans-serif; margin:0; padding:0;'>" +
                        "<div style='padding: 50px;'>" +
                        "<div style='max-width: 600px; margin: auto; background-color: white; border-radius: 5px; padding: 20px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);'>" +
                        "<h1 style='color: #445566; text-align:center;'>Welcome to Rent2Go!</h1>" +
                        "<p>Dear " + theUser.getName() + " " + theUser.getSurname() + ",</p>" +
                        "<p>Thank you for registering with us. To complete your registration, please click the button below:</p>" +
                        "<div style='text-align:center; margin-bottom:20px;'>" +
                        "<a href=\"" + url + "\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; border-radius:5px; box-shadow: 0px 3px 6px rgba(0,0,0,0.1); display: inline-block;'>Verify Your Email</a>" +
                        "</div>" +
                        "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                        "<p><a href=\"" + url + "\" style='color:#5D9CEC'>" + url + "</a></p>" +
                        "<p>Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!</p>" +
                        "<p style='border-top: 1px solid #DDDDDD; padding-top:20px; color:#888888'>Best Regards,<br>The Rent2Go Team</p>" +
                        "</div>" +
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

        messageHelper.setFrom("noreply@rentogo.com.tr", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(textContent, htmlContent);

        mailSender.send(message);
    }

    public void sendResetPasswordEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Reset Password";
        String senderName = "Rent2Go Company";

        String htmlContent =
                "<html>" +
                        "<body style='font-family: \"Roboto\", sans-serif; margin:0; padding:0;'>" +
                        "<div style='padding: 50px;'>" +
                        "<div style='max-width: 600px; margin: auto; background-color: white; border-radius: 5px; padding: 20px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);'>" +
                        "<h1 style='color: #445566; text-align:center;'>Reset Password</h1>" +
                        "<p>Dear " + theUser.getName() + " " + theUser.getSurname() + ",</p>" +
                        "<p>You have requested to reset your password. To complete your password reset, please click the button below:</p>" +
                        "<div style='text-align:center; margin-bottom:20px;'>" +
                        "<a href=\"" + url + "\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; border-radius:5px; box-shadow: 0px 3px 6px rgba(0,0,0,0.1); display: inline-block;'>Reset Password</a>" +
                        "</div>" +
                        "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                        "<p><a href=\"" + url + "\" style='color:#5D9CEC'>" + url + "</a></p>" +
                        "<p>If you did not request a password reset, please ignore this email.</p>" +
                        "<p style='border-top: 1px solid #DDDDDD; padding-top:20px; color:#888888'>Best Regards,<br>The Rent2Go Team</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>";

        String textContent =
                "Dear " + theUser.getName() + " " + theUser.getSurname() + ",\n" +
                        "You have requested to reset your password. To complete your password reset, please visit the following URL:\n" +
                        url + "\n" +
                        "If you did not request a password reset, please ignore this email.\n" +
                        "Best Regards,\nThe Rent2Go Team";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        messageHelper.setFrom("noreply@rentogo.com.tr", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(textContent, htmlContent);

        mailSender.send(message);
    }

    
}
