package edu.miu.cs.cs544.appointment.Repositories;

import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("SELECT distinct a from Appointment a  JOIN a.resevationList r " +
            " where a.startTime = :startAt" +
            " AND r.status = 'ACCEPTED'")
    List<Appointment> findAppointmentStartAtDateTime(
            @Param("startAt") LocalDateTime startAt);

}
