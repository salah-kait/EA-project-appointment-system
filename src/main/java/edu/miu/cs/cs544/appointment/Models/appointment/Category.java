package edu.miu.cs.cs544.appointment.Models.appointment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long defualtDuration;

}
