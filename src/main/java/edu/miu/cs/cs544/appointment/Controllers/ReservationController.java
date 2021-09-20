package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Services.ReservationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;


@RestController
@RequestMapping("api/reservation")
public class ReservationController{

    @Autowired
    private ReservationService reservationService;

    // Create user activation
    @PostMapping("/reserve")
    public ResponseEntity<?> createReservation(@RequestParam(name = "appointment_id", required = true) Long id,
                                               @RequestBody Reservation reservation) throws NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/reservation/reserve").toUriString());
        return ResponseEntity.created(uri).body(reservationService.createReservation(reservation, id));
    }


    // Get list of reservation(Paginated)
    @GetMapping(path = "reservations", params = "paged = true")
    public Page<Reservation> getReservations(Pageable pageable){
        return reservationService.getAllReservations(pageable);
    }


    // Update reservation
    @PutMapping("{id}")
    public ResponseEntity<Reservation> updateReservation(Long id, Reservation reservation){
        Reservation result = reservationService.updateReservation(id, reservation);

        if(result != null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


}
