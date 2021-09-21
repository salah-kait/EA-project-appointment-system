package edu.miu.cs.cs544.appointment.Payload.MessageQueueDTO;

import lombok.Data;

@Data
public class EmailDTO {
    private String subject;
    private String body;
    private String to;
}
