package edu.miu.cs.cs544.appointment.Models.appointment;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Future
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "catagoryId")
    private Category category;

    private Long duration;


    private String location;
    @ManyToOne
    private User provider;

    @OneToMany(mappedBy = "appointment")
    @JsonBackReference
    private List<Reservation> resevationList;

    public Appointment(Category category,LocalDateTime startTime, LocalDateTime endTime, Long duration, String location, User provider) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.location = location;
        this.provider = provider;
        this.category= category;

    }
    public Appointment(LocalDateTime startTime, LocalDateTime endTime, Long duration, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.location = location;
    }
}
