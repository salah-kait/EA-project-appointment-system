package edu.miu.cs.cs544.appointment;

import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;

@SpringBootApplication
public class EaCs544FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaCs544FinalProjectApplication.class, args);
    }

    @Bean
    public QueueMessagingTemplate defaultQueueMessagingTemplate(AmazonSQSAsync amazonSqs) {
        QueueMessagingTemplate queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
        return queueMessagingTemplate;
    }
}
