package edu.miu.cs.cs544.appointment.Util.Email;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailMessage {
    private  String subject;
    private  String body;
    private  String to;

}
