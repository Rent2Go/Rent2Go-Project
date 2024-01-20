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
       /* try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("erol.sehmus1@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

            throw new IllegalStateException("failed to send email");
        }*/
    }


}
