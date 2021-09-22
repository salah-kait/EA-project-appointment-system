package edu.miu.cs.cs544.appointment.Util.Email;

import edu.miu.cs.cs544.appointment.Util.SQS.SqsMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SqsMailService implements EmailService{


    @Autowired
    SqsMessageProducer sqsMessageProducer;

    @Autowired
    ApplicationContext context;

    @Override
    public void sendMessage(MailMessage mailMessage) {
        EmailQueueDTO emailSQSDto = context.getBean(EmailQueueDTO.class);
        emailSQSDto.setMailMessage(mailMessage);
        sqsMessageProducer.send(emailSQSDto);
    }
}
