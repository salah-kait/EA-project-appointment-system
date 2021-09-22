package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Exception.BadRequestException;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Security.CurrentUser;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Services.ReservationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("api/reservations")
public class ReservationController{

    @Autowired
    private ReservationService reservationService;

    // Create user activation
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservation reservation, @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(reservationService.createReservation(reservation, userPrincipal.getId()));
        }catch (NotFoundException e){
            return ResponseEntity.badRequest().build();
        }


    }


    // Get a reservation by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getReservation(@PathVariable Long id){
        try {
            Reservation reservation = reservationService.getReservation(id);
            return ResponseEntity.ok(reservation);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.badRequest().body("reservation not found");
        }
    }


    // Get list of reservation(Paginated)
    @GetMapping(params = "paged=true")
    @PreAuthorize("hasRole('PROVIDER') OR hasRole('CLIENT')")
    public Page<Reservation> getReservations(Pageable pageable, @CurrentUser UserPrincipal userPrincipal){
        return reservationService.getAllReservations(pageable, userPrincipal.getId());

    }


    // Update reservation
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER') OR hasRole('CLIENT')")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, CreateReservation createReservation){
        try {
            Reservation result = reservationService.updateReservation(id, createReservation);

            if(result != null){
                return ResponseEntity.ok(result);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }catch (NotFoundException e){
            return ResponseEntity.badRequest().build();
        }


    }

    @PreAuthorize("hasRole('PROVIDER')")
    @PatchMapping(path = "/admit/{id}")
    public ResponseEntity<Reservation> acceptReservation(@PathVariable Long id){
        try {
            return ResponseEntity.ok(reservationService.acceptReservation(id));
        }catch (NotFoundException e){
            return ResponseEntity.badRequest().build();
        }catch (IllegalStateException illegalStateException){
            return ResponseEntity.badRequest().build();
        }catch (BadRequestException badRequestException){
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('PROVIDER') OR hasRole('CLIENT')")
    @PatchMapping(path = "/cancel/{id}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id){

        try {
            return ResponseEntity.ok(reservationService.cancelReservation(id));
        }catch (NotFoundException e){
            return ResponseEntity.badRequest().build();
        }


    }
}
