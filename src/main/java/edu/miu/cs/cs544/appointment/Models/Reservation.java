package edu.miu.cs.cs544.appointment.Models;


import edu.miu.cs.cs544.appointment.Models.enums.ReservationStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private ReservationStatus status;

    @Column
    private LocalDateTime reservationTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Appointment appointment;

}
