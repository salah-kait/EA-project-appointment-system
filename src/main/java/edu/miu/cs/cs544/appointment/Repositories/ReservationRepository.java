package edu.miu.cs.cs544.appointment.Repositories;

import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAppointmentIdAndStatus(Long appointment_id, ReservationStatus status);
    Page<Reservation> findByUserId(Pageable pageable, Long id);
    List<Reservation> findByUserId(Long id);
    List<Reservation> findByAppointmentIdAndUserId(Long appointment_id, Long user_id);

}
