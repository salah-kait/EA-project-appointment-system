package edu.miu.cs.cs544.appointment.Schedules;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
public class AppointmentSchedule{

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
