package com.example.demo;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import java.io.IOException;
public class SendGridEmailer {
	public static void send(String f, String t, String s, String c) throws IOException {

        Email from = new Email("josephinestellar2021@srishakthi.ac.in");
        Email to = new Email(t); // use your own email address here

        String subject = s;
        Content content = new Content("text/html", c);

        Mail mail = new Mail(from, subject, to, content);

//        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        SendGrid sg = new SendGrid("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");
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
    }
}
