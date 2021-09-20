package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppointmentService {
     ApiResponse createAppointment(CreateAppointment createAppointment);
     ApiResponse updateAppointment(CreateAppointment createAppointment, Long id);
     List<Reservation> allResevations(Long appointmentId);
    ApiResponse DeleteAppointment(Long appointmentId);
    List<Reservation> allResevationsByStatus(Long appointmentId,String status);



}
