package edu.miu.cs.cs544.appointment.Models.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String title;
    private Long defualtDuration;

}
