package edu.miu.cs.cs544.appointment.Payload.Requests;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.enums.ReservationStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class CreateReservation {

    @NotBlank
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotBlank
    private LocalDateTime reservationDateTime;

    private User user;

    private Appointment appointment;

}
