package com.restApi.security.jwt.Api.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    public void sendEmail(String email) throws IOException {

        Mail mail = prepareEmail(email);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sendGrid.api(request);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
    }

    public Mail prepareEmail(String email) {

        Email from = new Email("arturofrodriguez11@gmail.com");
        Email to = new Email(email); 

        String subject = "Arturo's RestApi. Welcome email";
        Content content = new Content("text/html", "Thank you for been part of this proyect. <hr> Arturo's RestApi is ready for you. Have a nice day.");

        Mail mail = new Mail(from, subject, to, content);
        
        return mail;
    }
}
