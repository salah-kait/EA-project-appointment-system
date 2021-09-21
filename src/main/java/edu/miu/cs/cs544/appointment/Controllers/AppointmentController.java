package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;

import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentServiceImp;
    @PostMapping("/api/appointment")
    public ApiResponse createAppointment(@RequestBody CreateAppointment createAppointment){

        return appointmentServiceImp.createAppointment(createAppointment);
    }
    @PutMapping("/api/appointment/{id}")
    public ApiResponse updateAppointment(@RequestBody CreateAppointment createAppointment ,@PathVariable Long id){
return appointmentServiceImp.updateAppointment(createAppointment,id);
    }
    @GetMapping("/api/appointment/{id}/reservation")
    public List<Reservation> allResevations(@PathVariable Long id){
        return appointmentServiceImp.allResevations(id);

    }
    @DeleteMapping("/api/appointment/{id}")
    public ApiResponse DeleteAppointment(@PathVariable Long id){
       return appointmentServiceImp.DeleteAppointment(id);

    }



}
