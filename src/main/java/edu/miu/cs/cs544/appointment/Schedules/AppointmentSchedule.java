package edu.miu.cs.cs544.appointment.Schedules;

import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Services.AppointmentService;
import edu.miu.cs.cs544.appointment.Util.Email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@EnableAsync
@Component
public class AppointmentSchedule{

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    EmailSender emailSender;

    @Async
    @Scheduled(fixedRate = 60000)
    public void scheduleFixedRateTask() {
        System.out.println("Time:"+LocalDateTime.now().plusHours(1));
        List<Appointment> appointmentList = appointmentService.findAppointmentStartAtDateTime(LocalDateTime.now().plusHours(1));
        for (Appointment appointment: appointmentList) {
            emailSender.sendSimpleMessage(appointment.getProvider().getEmail(),"Appointment Reminder","You have an Appointment will start after an Hour");

        }
    }
}
