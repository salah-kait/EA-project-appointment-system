package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.ReservationRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Autowired
    ReservationService(ReservationRepository reservationRepository,
                       AppointmentRepository appointmentRepository,
                       UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    public Reservation createReservation(CreateReservation createReservation, Long userId) throws NotFoundException {


        Appointment appointment = appointmentRepository.findById(createReservation.getAppointmentId()).orElseThrow(() ->
                new NotFoundException("appointment not found")
        );

        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("user not found")
        );


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
    public Page<Reservation> getAllReservations(Pageable pageable, Long userId){
        Page<Reservation> reservations = reservationRepository.findByUserId(pageable, userId);
        return reservations;
    }

    public Reservation updateReservation(Long id, CreateReservation createReservation) throws NotFoundException {
        Reservation reservation1 = null;
        if (getReservation(id) != null) {
            reservation1 = getReservation(id);

            Appointment appointment = appointmentRepository.findById(createReservation.getAppointmentId()).orElseThrow(() ->
                    new NotFoundException("appointment not found")
            );

            // ToDo: check if the user is the right provider


            reservation1.setAppointment(appointment);
            reservation1.setReservationDateTime(createReservation.getReservationDateTime());
//            reservation1.setStatus(ReservationStatus.PENDING);

            reservationRepository.save(reservation1);

            return reservation1;

        } else
            return reservation1;
    }

    public Reservation getReservation(Long id) throws NotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("appointment not found")
        );

        // ToDo: check if the user is the right provider

        return reservation;
    }

    public List<Reservation> getReservationByAppointmentAndStatus(Long appointment_id, String status){
        List<Reservation> reservations = reservationRepository.findByAppointmentIdAndStatus(appointment_id, status);
        return reservations;
    }

    public Reservation acceptReservation(Long id) throws NotFoundException, IllegalStateException{
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("reservation not found")
        );


        // ToDo: check if the user is the right provider

        Long appointmentId = reservation.getAppointment().getId();

        // get accepted reservation for an appointment
        List<Reservation> reservationList = getReservationByAppointmentAndStatus(appointmentId, "ACCEPTED");

        // check if the appointment has already accept a reservation
        if(reservationList.size() > 0){
            throw new IllegalStateException("Appointment already reserved!");
        }
        reservation.setStatus(ReservationStatus.ACCEPTED);
        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Long id) throws NotFoundException {

        // ToDo: check if the user is the right provider

        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("reservation not found")
                );
        reservation.setStatus(ReservationStatus.CANCELED);
        return reservationRepository.save(reservation);
    }

}
