package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.constants.EmailConstants;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest.ContactFormRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
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
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, EmailConstants.EMAIL_ENCODING.getValue());

        messageHelper.setFrom(EmailConstants.EMAIL_FROM.getValue(), EmailConstants.EMAIL_FROM_NAME.getValue());
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
        String subject = EmailConstants.EMAIL_VERIFICATION_SUBJECT.getValue();

        String htmlContent = String.format(EmailConstants.EMAIL_VERIFICATION_HTML_CONTENT.getValue(), name, link, link, link);

        String textContent = String.format(EmailConstants.EMAIL_VERIFICATION_TEXT_CONTENT.getValue(), name, link);

        sendEmail(name, email, link, subject, htmlContent, textContent);

    }

    @Override
    public void sendResetPasswordEmail(String name,String email, String link) throws MessagingException, UnsupportedEncodingException {
        String subject = EmailConstants.EMAIL_RESET_PASSWORD_SUBJECT.getValue();
            String htmlContent =String.format(EmailConstants.EMAIL_RESET_PASSWORD_HTML_CONTENT.getValue(), name, link, link, link);

            String textContent =String.format(EmailConstants.EMAIL_RESET_PASSWORD_TEXT_CONTENT.getValue(), name, link);

        sendEmail(name, email, link, subject, htmlContent, textContent);

    }

    public void sendContactEmail(ContactFormRequest form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("support@rentogo.com.tr");
        message.setTo("contact.rent2go@gmail.com");
        message.setSubject("New Contact Form Submission");
        message.setText(
                "Name: " + form.getFirstName() + " " + form.getLastName() + "\n" +
                        "Email: " + form.getEmail() + "\n" +
                        "Phone: " + form.getPhone() + "\n" +
                        "Message: " + form.getMessage()
        );

        mailSender.send(message);
    }


}



