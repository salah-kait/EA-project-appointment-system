package edu.miu.cs.cs544.appointment.Controllers;


import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Security.CurrentUser;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import edu.miu.cs.cs544.appointment.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentServiceImp;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointment createAppointment,@CurrentUser UserPrincipal userPrincipal){
        try{
            return ResponseEntity.ok(appointmentServiceImp.createAppointment(createAppointment,userPrincipal.getId()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
    public ResponseEntity<?> updateAppointment(@RequestBody CreateAppointment createAppointment ,@PathVariable Long id){
        try{
            return  ResponseEntity.ok(appointmentServiceImp.updateAppointment(createAppointment,id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
    @GetMapping("/{id}/reservation")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
    public List<Reservation> allResevations(@PathVariable Long id){
        return appointmentServiceImp.allResevations(id);

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
    public ResponseEntity<?> DeleteAppointment(@PathVariable Long id)  {
        try{
            return ResponseEntity.ok(appointmentServiceImp.DeleteAppointment(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping(params = "paged=true")
    Page<Appointment> getAllAppointements(Pageable pageable){
        return  appointmentServiceImp.getAllAppointments(pageable);
    }
}
