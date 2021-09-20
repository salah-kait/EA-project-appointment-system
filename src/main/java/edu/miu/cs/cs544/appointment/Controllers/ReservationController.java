package edu.miu.cs.cs544.appointment.Controllers;

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
    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> createReservation(@RequestParam(name = "appointment_id", required = true) Long id,
                                               @RequestBody CreateReservation reservation) throws NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/reservation/reserve")
                .toUriString());
        return ResponseEntity.created(uri).body(reservationService.createReservation(reservation, id));
    }

    // Get list of reservation(Paginated)
    @GetMapping(path = "{userId}", params = "paged=true")
    @PreAuthorize("hasRole('CLIENT')")
    public Page<Reservation> getReservations(Pageable pageable, @CurrentUser UserPrincipal userPrincipal){
        return reservationService.getAllReservations(pageable, userPrincipal.getId());

    }


    // Update reservation
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER') OR hasRole('CLIENT')")
    public ResponseEntity<Reservation> updateReservation(Long id, CreateReservation createReservation){
        Reservation result = reservationService.updateReservation(id, createReservation);

        if(result != null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
