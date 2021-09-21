package edu.miu.cs.cs544.appointment.Util.SQS;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SqsMessageProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SqsMessageProducer(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public <T> void send(IQueueMessageDTO message, Map<String, Object> headers) {
        if (message == null) {
            log.warn("SQS Producer cant produce 'null' value");
            return;
        }

        log.debug(" Messgae {} " + message);
        log.debug(" Queue name {} " + message.getQueueName());
        queueMessagingTemplate.convertAndSend(message.getQueueName(), message.getMessage(),headers);
    }
    public <T> void send(IQueueMessageDTO message) {
        if (message == null) {
            log.warn("SQS Producer cant produce 'null' value");
            return;
        }

       log.debug(" Messgae {} " + message.getMessage());
       log.debug(" Queue name {} " + message.getQueueName());
        queueMessagingTemplate.convertAndSend(message.getQueueName(),message.getMessage());
    }

}