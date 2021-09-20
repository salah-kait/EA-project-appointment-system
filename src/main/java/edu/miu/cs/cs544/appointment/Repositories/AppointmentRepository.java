package edu.miu.cs.cs544.appointment.Repositories;

import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
