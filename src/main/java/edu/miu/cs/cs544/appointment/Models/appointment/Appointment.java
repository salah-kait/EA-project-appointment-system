package edu.miu.cs.cs544.appointment.Models.appointment;

import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startTime;
    private LocalDate endTime;
    @Embedded
    private Category category;

    private Long duration;


    private String location;
    @ManyToOne
    private User provider;
    @OneToMany
private List<Reservation> reservationList;

    public Appointment(Category category,LocalDate startTime, LocalDate endTime, Long duration, String location, User provider) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.location = location;
        this.provider = provider;
        this.category= category;

    }
    public Appointment(LocalDate startTime, LocalDate endTime, Long duration, String location,Category category) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.location = location;
        this.category= category;

    }
}
