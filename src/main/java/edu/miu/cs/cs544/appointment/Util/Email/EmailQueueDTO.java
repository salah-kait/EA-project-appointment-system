package edu.miu.cs.cs544.appointment.Util.Email;

import edu.miu.cs.cs544.appointment.Util.SQS.IQueueMessageDTO;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;


@Data
public class EmailQueueDTO implements IQueueMessageDTO<MailMessage> {

    @Value("${app.sqs.queue.messaging}")
    private String QueueName;

    private MailMessage mailMessage;
    private String body;
    private String to;

    public EmailQueueDTO(MailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    @Override
    public String getQueueName() {
            return QueueName;
    }

    @Override
    public MailMessage getMessage() {
        return mailMessage;
    }
}
