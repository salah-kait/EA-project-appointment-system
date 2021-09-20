package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.ReservationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    ReservationService(ReservationRepository reservationRepository, AppointmentRepository appointmentRepository) {
        this.reservationRepository = reservationRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Reservation createReservation(CreateReservation createReservation, Long id) throws NotFoundException {


        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("appointment not found")
        );

        createReservation.setAppointment(appointment);

        Reservation reservation = new Reservation(
                createReservation.getReservationStatus(),
                createReservation.getReservationDateTime()
        );

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

    public Reservation updateReservation(Long id, CreateReservation createReservation) {
        Reservation reservation1 = null;
        if (getReservation(id) != null) {
            reservation1 = getReservation(id);

            reservation1.setAppointment(createReservation.getAppointment());
            reservation1.setReservationDateTime(createReservation.getReservationDateTime());
//            reservation1.setStatus(ReservationStatus.PENDING);

            reservationRepository.save(reservation1);

            return reservation1;

        } else
            return reservation1;
    }

    public Reservation cancelReservation(Reservation reservation, Long id) {

        if (getReservation(id) != null) {
            reservation.setStatus(ReservationStatus.CANCELED);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation getReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isPresent())
            return reservation.get();
        else
            return null;
    }

}
