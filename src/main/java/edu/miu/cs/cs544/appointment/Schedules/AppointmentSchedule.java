package edu.miu.cs.cs544.appointment.Schedules;

import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Services.interfaces.AppointmentService;
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

        LocalDateTime datetime1 =  LocalDateTime.now().plusHours(1);
        LocalDateTime datetime2 =  datetime1.plusMinutes(1);
        System.out.println("Time:"+datetime1 +"  AND  "+datetime2);
        List<Appointment> appointmentList = appointmentService.findAppointmentStartAtDateTime(datetime1,datetime2);
        for (Appointment appointment: appointmentList) {
            emailSender.sendSimpleMessage(appointment.getProvider().getEmail(),"Appointment Reminder","You have an Appointment will start after an Hour");

        }
    }
}
