package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppointmentService {
     Appointment createAppointment(CreateAppointment createAppointment, Long id) throws NotFoundException;
    Appointment updateAppointment(CreateAppointment createAppointment, Long id) throws NotFoundException;
     List<Reservation> allResevations(Long appointmentId);
    ApiResponse DeleteAppointment(Long appointmentId);
    List<Reservation> allResevationsByStatus(Long appointmentId,String status);



}
