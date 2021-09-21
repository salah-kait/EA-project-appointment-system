package edu.miu.cs.cs544.appointment.Util.Email;

import edu.miu.cs.cs544.appointment.Util.SQS.SqsMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqsMailService implements EmailService{


    @Autowired
    SqsMessageProducer sqsMessageProducer;

    @Override
    public void sendMessage(MailMessage mailMessage) {
        sqsMessageProducer.send(new EmailQueueDTO(mailMessage));
    }
}
