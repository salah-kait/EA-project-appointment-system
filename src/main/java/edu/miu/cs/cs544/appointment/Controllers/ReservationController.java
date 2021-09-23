package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Security.CurrentUser;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Services.ReservationServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/reservations")
public class ReservationController{

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    // Create user activation
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservation reservation, @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(reservationServiceImpl.createReservation(reservation, userPrincipal.getId()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    // Get a reservation by Id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER') OR hasRole('CLIENT')")
    public ResponseEntity<?> getReservation(@PathVariable Long id){
        try {
            Reservation reservation = reservationServiceImpl.getReservation(id);
            return ResponseEntity.ok(reservation);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.badRequest().body("reservation not found");
        }
    }


    // Get list of reservation(Paginated)
    @GetMapping(params = "paged=true")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER') OR hasRole('CLIENT')")
    public Page<Reservation> getReservations(Pageable pageable, @CurrentUser UserPrincipal userPrincipal){
        return reservationServiceImpl.getUserReservations(pageable, userPrincipal.getId());
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
    @PatchMapping(path = "/admit/{id}")
    public ResponseEntity<?> acceptReservation(@PathVariable Long id){
        try {
            return ResponseEntity.ok(reservationServiceImpl.acceptReservation(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(false,e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENT')")
    @PatchMapping(path = "/cancel/{id}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id){

        try {
            return ResponseEntity.ok(reservationServiceImpl.cancelReservation(id));
        }catch (NotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
