package edu.miu.cs.cs544.appointment.Repositories;

import edu.miu.cs.cs544.appointment.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
