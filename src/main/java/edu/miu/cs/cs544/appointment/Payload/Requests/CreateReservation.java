package edu.miu.cs.cs544.appointment.Payload.Requests;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateReservation {

    @NotBlank
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private LocalDate reservationDateTime;

    private Long userId;

    @NotBlank
    private Long appointmentId;

}
