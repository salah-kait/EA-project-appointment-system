package edu.miu.cs.cs544.appointment.Services.interfaces;

import edu.miu.cs.cs544.appointment.Exception.BadRequestException;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.ReservationRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

        public Reservation createReservation(CreateReservation createReservation, Long userId) throws NotFoundException;

        public Page<Reservation> getUserReservations(Pageable pageable, Long userId);

        public Reservation getReservation(Long id) throws NotFoundException ;

        public List<Reservation> getReservationByAppointmentAndStatus(Long appointment_id, ReservationStatus status);

        public Reservation acceptReservation(Long id) throws NotFoundException, IllegalStateException;

        public Reservation cancelReservation(Long id) throws NotFoundException ;

}
