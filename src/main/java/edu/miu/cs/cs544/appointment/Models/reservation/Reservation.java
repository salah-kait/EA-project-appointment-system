package edu.miu.cs.cs544.appointment.Models.reservation;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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

    @NotBlank
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @NotBlank
    private LocalDateTime reservationDateTime;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Appointment appointment;

    public Reservation(){}

    public Reservation(ReservationStatus status,LocalDateTime reservationDateTime){
        this.status = status;
        this.reservationDateTime = reservationDateTime;
    }

}
