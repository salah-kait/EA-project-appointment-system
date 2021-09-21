package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.enums.ReservationStatus;
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
    ReservationService(ReservationRepository reservationRepository,AppointmentRepository appointmentRepository){
        this.reservationRepository = reservationRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Reservation createReservation(Reservation reservation,Long id) throws NotFoundException {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("appointment not found")
        );

        reservation.setAppointment(appointment);
        reservation.setStatus(ReservationStatus.PENDING);
        return reservationRepository.save(reservation);


    }

    public Page<Reservation> getAllReservations(Pageable pageable){
        return reservationRepository.findAll(pageable);
    }

    public Reservation updateReservation(Long id, Reservation reservation){
        Reservation reservation1 = null;
        if(getReservation(id) != null){
            reservation1 = getReservation(id);

            reservation1.setAppointment(reservation.getAppointment());
            reservation1.setReservationDateTime(reservation.getReservationDateTime());
//            reservation1.setStatus(ReservationStatus.PENDING);

            reservationRepository.save(reservation);

            return reservation1;

        } else
        return reservation1;
    }

    public Reservation cancelReservation(Reservation reservation, Long id){
        if(getReservation(id) != null){
            reservation.setStatus(ReservationStatus.CANCELED);
            reservationRepository.save(reservation);
            return reservation;
        }
        return null;
    }

    public Reservation getReservation(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if(reservation.isPresent())
            return reservation.get();
        else
            return null;
    }

}
