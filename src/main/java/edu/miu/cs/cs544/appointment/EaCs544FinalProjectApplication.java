package edu.miu.cs.cs544.appointment;


import com.amazonaws.services.sqs.AmazonSQSAsync;
import edu.miu.cs.cs544.appointment.Models.Role;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.enums.RoleName;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy
@EnableScheduling
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
