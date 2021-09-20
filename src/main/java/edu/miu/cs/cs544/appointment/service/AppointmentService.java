package edu.miu.cs.cs544.appointment.service;

import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppointmentService {
    public ApiResponse createAppointment(CreateAppointment createAppointment);
    public ApiResponse updateAppointment(CreateAppointment createAppointment, Long id);
    public List<Reservation> allResevations(Long appointmentId);
    public ApiResponse DeleteAppointment(Long appointmentId);
    public List<Reservation> allResevationsByStatus(Long appointmentId,String status);



}
