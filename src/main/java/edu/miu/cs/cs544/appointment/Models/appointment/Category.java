package edu.miu.cs.cs544.appointment.Models.appointment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long defualtDuration;
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Appointment> appointment;

    public Category(String title, Long defualtDuration) {
        this.title = title;
        this.defualtDuration = defualtDuration;
    }
}
