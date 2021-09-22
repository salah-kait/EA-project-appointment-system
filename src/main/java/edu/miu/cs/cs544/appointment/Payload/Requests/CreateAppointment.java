package edu.miu.cs.cs544.appointment.Payload.Requests;
 import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
 import com.fasterxml.jackson.databind.annotation.JsonSerialize;
 import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
 import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
 import edu.miu.cs.cs544.appointment.Models.User;
 import edu.miu.cs.cs544.appointment.Models.appointment.Category;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;
 import org.springframework.format.annotation.DateTimeFormat;

 import javax.validation.constraints.NotBlank;
 import java.time.LocalDate;
 import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointment {
   @NotBlank
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;
    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private Long duration;

    @NotBlank
    private String location;

    private Long categoryId;
}
