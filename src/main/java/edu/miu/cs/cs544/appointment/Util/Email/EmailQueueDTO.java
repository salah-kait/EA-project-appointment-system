package edu.miu.cs.cs544.appointment.Util.Email;

import edu.miu.cs.cs544.appointment.Util.SQS.IQueueMessageDTO;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmailQueueDTO implements IQueueMessageDTO<MailMessage> {

    @Value("${app.sqs.queue.messaging}")
    private String QueueName;

    private MailMessage mailMessage;
    private String body;
    private String to;

    @Override
    public String getQueueName() {
            return QueueName;
    }

    @Override
    public MailMessage getMessage() {
        return mailMessage;
    }
}
