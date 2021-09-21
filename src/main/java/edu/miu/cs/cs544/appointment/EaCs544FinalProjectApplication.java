package edu.miu.cs.cs544.appointment;


import edu.miu.cs.cs544.appointment.Models.Role;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.enums.RoleName;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EaCs544FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaCs544FinalProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AppointmentRepository appointmentRepository, RoleRepository roleRepository){
        return args -> {

//            Appointment appointment = new Appointment(null, null, null, "Fairfield", null);
//            roleRepository.save(new Role(1L, RoleName.ROLE_CLIENT));
//            roleRepository.save(new Role(2L, RoleName.ROLE_PROVIDER));
//            roleRepository.save(new Role(3L, RoleName.ROLE_ADMIN));
        };
    }

}
