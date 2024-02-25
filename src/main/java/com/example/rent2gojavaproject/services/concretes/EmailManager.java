package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.constants.EmailConstants;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.abstracts.MailConfigurationService;
import com.example.rent2gojavaproject.services.dtos.requests.contactFormRequest.ContactFormRequest;
import com.example.rent2gojavaproject.services.dtos.requests.reservationDetailRequest.AddReservationDetailRequest;
import com.example.rent2gojavaproject.services.dtos.responses.mailConfigurationResponse.GetMailConfigurationResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailManager implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final MailConfigurationService mailConfigurationService;

    @Override
    public JavaMailSender getJavaMailSender() {
        DataResult<GetMailConfigurationResponse> mailConfiguration = mailConfigurationService.getMailConfiguration();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfiguration.getData().getHost());
        mailSender.setPort(mailConfiguration.getData().getPort());
        mailSender.setUsername(mailConfiguration.getData().getUsername());
        mailSender.setPassword(mailConfiguration.getData().getPassword());

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

    @Override
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

    @Override
    public void sendThankYouEmail(ContactFormRequest form) throws MessagingException, UnsupportedEncodingException {
        JavaMailSender mailSender = getJavaMailSender();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("noreply@rentogo.com.tr","Rent2Go Company");
        helper.setTo(form.getEmail());
        helper.setSubject("Thank you for contacting us");

        String htmlMsg = "<div style=\"font-family: Arial, sans-serif; color: #333;\">"
                + "<p style=\"font-size: 18px;\">Dear " + form.getFirstName() + " " + form.getLastName() + ",</p>"
                + "<p>We acknowledge the receipt of your message and extend our gratitude for your interest. Our relevant department is currently reviewing your inquiry and will provide you with a response promptly.</p>"
                + "<p>We aim to get back to you within 2 days at the latest. Please rest assured that we are readily available to address any questions or requests you may have.</p>"
                + "<p>Should you require further information about our company and the services we offer, please do not hesitate to contact us. We are here to assist you in the best possible manner.</p>"
                + "<p><b>Your Message:</b> " + form.getMessage() + "</p>"
                + "<p>Best Regards,</p>"
                + "<p><b style=\"color: #007BFF;\">Rent2Go Team</b></p>"
                + "</div>";

        String textMsg = "Dear " + form.getFirstName() + " " + form.getLastName() + ",\n"
                + "We acknowledge the receipt of your message and extend our gratitude for your interest. Our relevant department is currently reviewing your inquiry and will provide you with a response promptly.\n"
                + "We aim to get back to you within 2 days at the latest. Please rest assured that we are readily available to address any questions or requests you may have.\n"
                + "Should you require further information about our company and the services we offer, please do not hesitate to contact us. We are here to assist you in the best possible manner.\n"
                + "Your Message: " + form.getMessage() + "\n"
                + "Best Regards,\n"
                + "Rent2Go Team";

        helper.setText(textMsg, htmlMsg);

        mailSender.send(message);
    }

    @Override
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

    @Override
    public  void cashReservationDetail (AddReservationDetailRequest form) throws MessagingException, UnsupportedEncodingException {
        JavaMailSender mailSender = getJavaMailSender();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(EmailConstants.EMAIL_FROM.getValue(), EmailConstants.EMAIL_FROM_NAME.getValue());
        helper.setTo(form.getEmail());
        helper.setSubject(EmailConstants.RESERVATION_CONFIRMATION_SUBJECT.getValue());

        String htmlMsg = String.format(EmailConstants.RESERVATION_CONFIRMATION_HTML_CONTENT.getValue(),
                form.getName(),
                form.getName(),
                form.getEmail(),
                form.getPhone(),
                form.getStartDate(),
                form.getEndDate(),
                form.getTotalDay(),
                form.getPlate(),
                form.getCarInfo(),
                form.getTotalPrice());


        String textMsg = String.format(EmailConstants.RESERVATION_CONFIRMATION_TEXT_CONTENT.getValue(),
                form.getName(),
                form.getName(),
                form.getEmail(),
                form.getPhone(),
                form.getStartDate(),
                form.getEndDate(),
                form.getTotalDay(),
                form.getPlate(),
                form.getCarInfo(),
                form.getTotalPrice());

        helper.setText(textMsg, htmlMsg);

        mailSender.send(message);
    }




}



