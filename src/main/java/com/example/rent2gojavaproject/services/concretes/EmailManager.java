package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.constants.EmailConstants;
import com.example.rent2gojavaproject.models.MailConfiguration;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.abstracts.MailConfigurationService;
import com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest.ContactFormRequest;
import com.example.rent2gojavaproject.services.dtos.requests.reservationDetailRequest.AddReservationDetailRequest;
import com.example.rent2gojavaproject.services.dtos.responses.mailConfigurationResponse.GetMailConfigurationResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailManager implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final MailConfigurationService mailConfigurationService;

    private JavaMailSender getJavaMailSender() {
        GetMailConfigurationResponse mailConfiguration = mailConfigurationService.getMailConfiguration();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfiguration.getHost());
        mailSender.setPort(mailConfiguration.getPort());
        mailSender.setUsername(mailConfiguration.getUsername());
        mailSender.setPassword(mailConfiguration.getPassword());

        return mailSender;
    }

    @Override
    public void sendEmail(String name, String email, String link, String subject, String htmlContent, String textContent) throws MessagingException, UnsupportedEncodingException {
        JavaMailSender mailSender = getJavaMailSender();

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

    public void sendContactEmail(ContactFormRequest form) throws MessagingException, UnsupportedEncodingException {
        JavaMailSender mailSender = getJavaMailSender();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("support@rentogo.com.tr","Rent2Go Company");
        helper.setTo("contact.rent2go@gmail.com");
        helper.setSubject("New Contact Form Submission");

        String htmlMsg = "<h1>New Contact Form Submission</h1>"
                + "<p><b>Name:</b> " + form.getFirstName() + " " + form.getLastName() + "</p>"
                + "<p><b>Email:</b> " + form.getEmail() + "</p>"
                + "<p><b>Phone:</b> " + form.getPhone() + "</p>"
                + "<p><b>Message:</b> " + form.getMessage() + "</p>";

        String textMsg = "New Contact Form Submission\n"
                + "Name: " + form.getFirstName() + " " + form.getLastName() + "\n"
                + "Email: " + form.getEmail() + "\n"
                + "Phone: " + form.getPhone() + "\n"
                + "Message: " + form.getMessage();

        helper.setText(textMsg, htmlMsg);

        mailSender.send(message);
    }

    public void reservationDetailEmail(AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException {
        JavaMailSender mailSender = getJavaMailSender();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(EmailConstants.EMAIL_FROM.getValue(), EmailConstants.EMAIL_FROM_NAME.getValue());
        helper.setTo(form.getEmail());
        helper.setSubject(EmailConstants.RESERVATION_DETAIL_SUBJECT.getValue());

        String htmlMsg = String.format(EmailConstants.RESERVATION_DETAIL_HTML_CONTENT.getValue(),
                form.getName(),
                form.getEmail(),
                form.getPhone(),
                form.getStartDate(),
                form.getEndDate(),
                form.getTotalDay(),
                form.getPlate(),
                form.getCarInfo(),
                form.getTotalPrice(),
                form.getName());

        String textMsg = String.format(EmailConstants.RESERVATION_DETAIL_TEXT_CONTENT.getValue(),
                form.getName(),
                form.getEmail(),
                form.getPhone(),
                form.getStartDate(),
                form.getEndDate(),
                form.getTotalDay(),
                form.getPlate(),
                form.getCarInfo(),
                form.getTotalPrice(),
                form.getName());

        helper.setText(textMsg, htmlMsg);

        mailSender.send(message);
    }




}



