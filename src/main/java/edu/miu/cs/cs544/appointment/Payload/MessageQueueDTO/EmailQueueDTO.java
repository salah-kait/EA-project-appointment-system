package edu.miu.cs.cs544.appointment.Payload.MessageQueueDTO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailQueueDTO implements IQueueMessageDTO {

    @Value("${app.sqs.queue.messaging}")
    private String QueueName;

    private String subject;
    private String body;
    private String to;


    @Override
    public String getQueueName() {
            return QueueName;
    }
}
