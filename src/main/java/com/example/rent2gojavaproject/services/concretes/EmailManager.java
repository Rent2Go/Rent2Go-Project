package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class EmailManager implements EmailSenderService {

    private final SendGrid sendGrid;



    @Override
    public void send(String to, String email) {




        Email from = new Email("rent2go@feyzaerat.com.tr");
        String subject = "Sending with SendGrid is Fun";
        Email to1 = new Email(to);
        Content content = new Content("text/html", email);
        Mail mail = new Mail(from, subject, to1, content);


        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
           throw new RuntimeException(ex.getMessage());
        }

    }
    @Override
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
