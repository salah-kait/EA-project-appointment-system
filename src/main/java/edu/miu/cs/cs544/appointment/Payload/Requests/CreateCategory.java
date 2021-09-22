package edu.miu.cs.cs544.appointment.Payload.Requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCategory {
    @NotBlank
    private String title;
    @NotBlank
    private Long defualtDuration;
}
