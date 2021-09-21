package edu.miu.cs.cs544.appointment.Util.Email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmailSender{

    @Autowired
    EmailService emailService;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        emailService.sendMessage(new MailMessage(subject,text,to));

    }
}

