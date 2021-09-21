package edu.miu.cs.cs544.appointment.Models.reservation;


import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "reservations")
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

//    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column
    private ReservationStatus status;

//    @NotBlank
//    @Column
    private LocalDate reservationDateTime;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Appointment appointment;

    public Reservation(){}

    public Reservation(ReservationStatus status,LocalDate reservationDateTime){
        this.status = status;
        this.reservationDateTime = reservationDateTime;
    }

}
