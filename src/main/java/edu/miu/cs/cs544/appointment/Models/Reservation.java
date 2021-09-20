package edu.miu.cs.cs544.appointment.Models;


import edu.miu.cs.cs544.appointment.Models.enums.ReservationStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column
    private ReservationStatus status;

    @NotBlank
    @Column
    private LocalDateTime reservationDateTime;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Appointment appointment;

}
