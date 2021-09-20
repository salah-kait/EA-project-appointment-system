package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.Appointment;
import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Models.enums.ReservationStatus;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public Reservation createReservation(Reservation reservation,Long id){

        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment == null)
            return null;
        else {
            Appointment appointment1 = appointment.get();
            reservation.setAppointment(appointment1);
            reservation.setStatus(ReservationStatus.PENDING);
            return reservationRepository.save(reservation);
        }

    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Long id, Reservation reservation){
        Reservation reservation1 = null;
        if(reservationExist(id) != null){
            reservation1 = reservationExist(id);

            reservation1.setAppointment(reservation.getAppointment());
            reservation1.setReservationDateTime(reservation.getReservationDateTime());
            reservation1.setStatus(ReservationStatus.PENDING);

            reservationRepository.save(reservation1);

            return reservation1;
        } else
        return reservation1;
    }

    public Reservation cancelReservation(Reservation reservation, Long id){
        if(reservationExist(id) != null){
            reservation.setStatus(ReservationStatus.CANCELED);
            reservationRepository.save(reservation);
            return reservation;
        }
        return null;
    }

    public Reservation reservationExist(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if(reservation.isPresent())
            return reservation.get();
        else
            return null;
    }

}
