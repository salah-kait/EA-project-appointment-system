package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/reservation")
public class ReservationController{

    @Autowired
    private ReservationService reservationService;

    // Create user activation
    @PostMapping("/reserve")
    public ResponseEntity<?> createReservation(@RequestParam(name = "appointment_id", required = true) Long id,
                                               @RequestBody Reservation reservation){

    }


    // Get list of reservation(Paginated)

    // Update reservation

}
