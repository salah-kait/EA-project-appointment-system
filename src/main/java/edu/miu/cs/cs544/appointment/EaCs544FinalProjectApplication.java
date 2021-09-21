package edu.miu.cs.cs544.appointment;

import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class EaCs544FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaCs544FinalProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AppointmentRepository appointmentRepository){
        return args -> {
            appointmentRepository.save(new Appointment(null,"Fairfield",new ArrayList<>()));
            appointmentRepository.save(new Appointment(null,"Iowa",new ArrayList<>()));
            appointmentRepository.save(new Appointment(null,"Des Moins",new ArrayList<>()));
            appointmentRepository.save(new Appointment(null,"Chicago",new ArrayList<>()));
        };
    }

}
