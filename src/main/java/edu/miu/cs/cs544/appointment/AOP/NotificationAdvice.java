package edu.miu.cs.cs544.appointment.AOP;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Util.Email.EmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class NotificationAdvice {
    @Autowired
    private final EmailSender emailSender;
    public NotificationAdvice(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @AfterReturning(value = "execution(* edu.miu.cs.cs544.appointment.Services.AppointmentServiceImp.createAppointment(..))", returning = "appointment")
    public void afterReturningAdviceCreateAppointment(JoinPoint joinPoint, Appointment appointment) {
        emailSender.sendSimpleMessage(appointment.getProvider().getEmail(),"You Created An Appointment","You Created An Appointment");
    }


    @AfterReturning(value = "execution(* edu.miu.cs.cs544.appointment.Services.interfaces.UserService.save(..))", returning = "user")
    public void afterReturningAdviceCreateUser(JoinPoint joinPoint, User user) {
        emailSender.sendSimpleMessage(user.getEmail(),"Welcome To Appointment System","Welcome To Appointment System");
    }

    @Async
    @AfterReturning(value = "execution(* edu.miu.cs.cs544.appointment.Services.AppointmentServiceImp.updateAppointment(..))", returning = "appointment")
    public void afterReturningAdviceUpdatingAppointment(JoinPoint joinPoint, Appointment appointment) {
        emailSender.sendSimpleMessage(appointment.getProvider().getEmail(),"Appointment Updates","Your Appointment updated");
    }
    @Async
    @After(value = "execution(* edu.miu.cs.cs544.appointment.Services.AppointmentServiceImp.DeleteAppointment(..))")
    public void afterReturningAdviceDeleteAppointment(JoinPoint joinPoint) {
        emailSender.sendSimpleMessage("f2@dd.con","delete","appointment deleted");
    }
//
//    @Async
//    @AfterReturning(value = "execution(* miu.edu.ea.cs544.ars.service.AppointmentService.update(..))", returning = "appointment")
//    public void afterReturningAdviceUpdateAppointment(JoinPoint joinPoint, Appointment appointment) {
//        emailSender.sendCustomerAppointmentNotification(appointment, false);
//        emailSender.sendProviderAppointmentNotification(appointment, false);
//    }
}