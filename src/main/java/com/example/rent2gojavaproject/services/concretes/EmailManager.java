package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailManager implements EmailSenderService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String name, String email, String link, String subject, String htmlContent, String textContent) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        messageHelper.setFrom("noreply@rentogo.com.tr", "Rent2Go Company");
        messageHelper.setTo(email);
        messageHelper.setSubject(subject);
        messageHelper.setText(textContent, htmlContent);

        mailSender.send(message);
    }

    @Override
    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @Override
    public void buildEmail(String name,String email, String link) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String htmlContent =
                "<html>" +
                        "<body style='font-family: \"Roboto\", sans-serif; margin:0; padding:0;'>" +
                        "<div>" +
                        "<div style='background-color: white; border-radius: 5px; padding: 5px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);'>" +
                        "<h1 style='color: #445566; text-align:left;'>Welcome to Rent2Go!</h1>" +
                        "<p>Dear " + name + ",</p>" +
                        "<p>Thank you for registering with us. To complete your registration, please click the button below:</p>" +
                        "<div style='text-align:left; margin-bottom:20px;'>" +
                        "<a href=\"" + link + "\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; border-radius:5px; box-shadow: 0px 3px 6px rgba(0,0,0,0.1); display: inline-block;'>Verify Your Email</a>" +
                        "</div>" +
                        "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                        "<p><a href=\"" + link + "\" style='color:#5D9CEC'>" + link + "</a></p>" +
                        "<p>Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!</p>" +
                        "<p style='border-top: 1px solid #DDDDDD; padding-top:20px; color:#888888'>Best Regards,<br>The Rent2Go Team</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                "</html>";

        String textContent =
                "Dear " + name + ",\n" +
                        "Thank you for registering with us. To complete your registration, please visit the following URL:\n" +
                        link + "\n" +
                        "Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!\n" +
                        "Best Regards,\nThe Rent2Go Team";

        sendEmail(name, email, link, subject, htmlContent, textContent);

    }

    @Override
    public void sendResetPasswordEmail(String name,String email, String link) throws MessagingException, UnsupportedEncodingException {
            String subject = "Password Reset Request";
            String htmlContent =
                    "<html>" +
                            "<body style='font-family: \"Roboto\", sans-serif; margin:0; padding:0;'>" +
                            "<div>" +
                            "<div style='background-color: white; border-radius: 5px; padding: 20px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);'>" +
                            "<h1 style='color: #445566; text-align:left;'>Rent2Go Password Reset</h1>" +
                            "<p>Dear " + name + ",</p>" +
                            "<p>You have requested to reset your password. Please click the button below to set a new password:</p>" +
                            "<div style='text-align:left; margin-bottom:20px;'>" +
                            "<a href=\"" + link + "\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; border-radius:5px; box-shadow: 0px 3px 6px rgba(0,0,0,0.1); display: inline-block;'>Reset Password</a>" +
                            "</div>" +
                            "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                            "<p><a href=\"" + link + "\" style='color:#5D9CEC'>" + link + "</a></p>" +
                            "<p>If you did not request a password reset, please ignore this email.</p>" +
                            "<p style='border-top: 1px solid #DDDDDD; padding-top:20px; color:#888888'>Best Regards,<br>The Rent2Go Team</p>" +
                            "</div>" +
                            "</div>" +
                            "</body>" +
                            "</html>";

            String textContent =
                    "Dear " + name + ",\n" +
                            "You have requested to reset your password. Please visit the following URL to set a new password:\n" +
                            link + "\n" +
                            "If you did not request a password reset, please ignore this email.\n" +
                            "Best Regards,\nThe Rent2Go Team";

        sendEmail(name, email, link, subject, htmlContent, textContent);

    }


}



