package edu.miu.cs.cs544.appointment.Util.Email;

import edu.miu.cs.cs544.appointment.Util.SQS.IQueueMessageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
public class EmailQueueDTO implements IQueueMessageDTO {

    @Value("${app.sqs.queue.messaging}")
    private String QueueName;

    private String subject;
    private String body;
    private String to;

    public EmailQueueDTO(String subject, String body, String to) {
        this.subject = subject;
        this.body = body;
        this.to = to;
    }

    @Override
    public String getQueueName() {
            return QueueName;
    }
}
