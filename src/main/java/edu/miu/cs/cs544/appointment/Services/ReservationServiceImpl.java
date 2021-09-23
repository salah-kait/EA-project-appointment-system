package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Exception.BadRequestException;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.ReservationRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import edu.miu.cs.cs544.appointment.Services.interfaces.ReservationService;
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

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Autowired
    ReservationServiceImpl(ReservationRepository reservationRepository,
                           AppointmentRepository appointmentRepository,
                           UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reservation createReservation(CreateReservation createReservation, Long userId) throws NotFoundException {


        Appointment appointment = appointmentRepository.findById(createReservation.getAppointmentId()).orElseThrow(() ->
                new NotFoundException("appointment not found")
        );

        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("user not found")
        );


        if(isAlreadyRequested(appointment.getId(), userId)){
            throw new BadRequestException("You already requested a reservation to this appointment");
        }


        createReservation.setReservationDateTime(LocalDateTime.now());
        createReservation.setReservationStatus(ReservationStatus.PENDING);

        Reservation reservation = new Reservation(
                createReservation.getReservationStatus(),
                createReservation.getReservationDateTime()
        );

        reservation.setUser(user);
        reservation.setAppointment(appointment);

        return reservationRepository.save(reservation);
    }

    /**
     * Get list of reservation for a client
     *
     * @param pageable
     * @param userId
     * @return
     */
    @Override
    public Page<Reservation> getUserReservations(Pageable pageable, Long userId){
        Page<Reservation> reservations = reservationRepository.findByUserId(pageable, userId);
        return reservations;
    }

    @Override
    public Reservation getReservation(Long id) throws NotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("reservation not found")
        );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)authentication.getPrincipal();

        if(reservation.getUser().getId() != currentUser.getId()){
            throw new BadRequestException("unauthorized access detected");
        }

        return reservation;
    }

    @Override
    public List<Reservation> getReservationByAppointmentAndStatus(Long appointment_id, ReservationStatus status){
        List<Reservation> reservations = reservationRepository.findByAppointmentIdAndStatus(appointment_id, status);
        return reservations;
    }

    @Override
    public Reservation acceptReservation(Long id) throws NotFoundException, IllegalStateException{
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("reservation not found")
        );

        checkUserEligibility(reservation);

        Long appointmentId = reservation.getAppointment().getId();

        // get accepted reservation for an appointment
        List<Reservation> reservationList = getReservationByAppointmentAndStatus(appointmentId, ReservationStatus.ACCEPTED);

        // check if the appointment has already accept a reservation
        if(reservationList.size() > 0){
            throw new IllegalStateException("Appointment already reserved!");
        }
        reservation.setStatus(ReservationStatus.ACCEPTED);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation cancelReservation(Long id) throws NotFoundException {

        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("reservation not found")
                );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)authentication.getPrincipal();

        if(reservation.getUser().getId() != currentUser.getId()){
            throw new BadRequestException("unauthorized access detected");
        }
        reservation.setStatus(ReservationStatus.CANCELED);
        return reservationRepository.save(reservation);
    }

    /**
     * Check if the user is eligible to certain task
     * @param reservation
     */
    public void checkUserEligibility(Reservation reservation){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)authentication.getPrincipal();

        if(reservation.getAppointment().getProvider().getId() != currentUser.getId()){
            throw new BadRequestException("unauthorized access detected");
        }
    }

    public boolean isAlreadyRequested(Long appointmentId, Long userId){
        List<Reservation> reservations = reservationRepository.findByAppointmentIdAndUserId(appointmentId, userId);

        if(reservations.size() > 0){
            return true;
        }

        return false;
    }

}
