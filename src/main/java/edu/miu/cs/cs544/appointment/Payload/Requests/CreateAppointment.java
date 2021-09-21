package edu.miu.cs.cs544.appointment.Payload.Requests;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data

public class CreateAppointment {
    @NotBlank
    private LocalDate startTime;
    @NotBlank
    private LocalDate endTime;
    //@NotBlank
    private Long duration;

    @NotBlank
    private String location;

    private User provider;
    private Category category;
}
