package com.linkedin.backend.features.authentication.utils;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String content) throws IOException {
        Email from = new Email("kumar08deepanshu@gmail.com");
        Email recipient = new Email(to);
        Content mailContent = new Content("text/plain", content);

        Mail mail = new Mail(from, subject, recipient, mailContent);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println("SendGrid Response: " + response.getStatusCode());
            System.out.println(response.getBody());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
