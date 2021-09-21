package edu.miu.cs.cs544.appointment.Service;


import edu.miu.cs.cs544.appointment.Models.Role;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import edu.miu.cs.cs544.appointment.Models.enums.RoleName;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.reservation.ReservationStatus;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateReservation;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.ReservationRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Services.ReservationService;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class ReservationServiceUnitTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    UserRepository userRepository;

    @Captor
    ArgumentCaptor<Reservation>  reservationArgumentCaptor;


    Role role_provider, role_client;
    Category category;
    User user1, user2, user3, user4;
    Appointment appointment;
    CreateReservation createReservation1, createReservation2, createReservation3, createReservation4;

    Reservation reservation1, reservation2, reservation3, reservation4;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        role_provider = new Role(1L, RoleName.ROLE_PROVIDER);
        role_client = new Role(1L, RoleName.ROLE_CLIENT);
        category = new Category("TM", 60L);
        user1 = new User("test1", "test1", "test@mail.com", "1234");
        user1.setRoles(new HashSet<Role>(Arrays.asList(role_provider)));
        appointment = new Appointment(category, LocalDate.of(2021, 12, 1), LocalDate.of(2021, 12, 1), 30L, "Berlington", user1);

        user2 = new User("test2", "test2", "test2@mail.com", "1234");
        user2.setRoles(new HashSet<Role>(Arrays.asList(role_client)));

        user3 = new User("test3", "test3", "test3@gmail.com", "1234");
        user4 = new User("test4", "test4", "test4@gmail.com", "1234");

        user3.setRoles(new HashSet<Role>(Arrays.asList(role_client)));
        user4.setRoles(new HashSet<Role>(Arrays.asList(role_client)));


        createReservation1 = new CreateReservation(ReservationStatus.PENDING, LocalDate.now(), 3L, 1L);

        createReservation2 = new CreateReservation(ReservationStatus.PENDING, LocalDate.now(), 3L, 1L);

        createReservation3 = new CreateReservation(ReservationStatus.PENDING, LocalDate.now(), 4L, 1L);

        createReservation4 = new CreateReservation(ReservationStatus.PENDING, LocalDate.now(), 4L, 1L);

        reservation1 = new Reservation(1L, ReservationStatus.PENDING, LocalDate.of(2021, 2, 3), user3, appointment);

        reservation2 = new Reservation(2L, ReservationStatus.PENDING, LocalDate.of(2021, 2, 3), user3, appointment);

        reservation3 = new Reservation(3L, ReservationStatus.PENDING, LocalDate.of(2021, 2, 3), user4, appointment);

        reservation4 = new Reservation(4L, ReservationStatus.PENDING, LocalDate.of(2021, 2, 3), user4, appointment);


        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));
        when(userRepository.findById(4L)).thenReturn(Optional.of(user4));

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation1));
        when(reservationRepository.findById(2L)).thenReturn(Optional.of(reservation2));
        when(reservationRepository.findById(3L)).thenReturn(Optional.of(reservation3));
        when(reservationRepository.findById(4L)).thenReturn(Optional.of(reservation4));


    }

    @Test
    public void createReservationTest() throws NotFoundException {


        CreateReservation createReservation = new CreateReservation(ReservationStatus.PENDING, LocalDate.now(), 2L, 1L);


        reservationService.createReservation(createReservation, 2L);

        then(reservationRepository).should().save(reservationArgumentCaptor.capture());

        Reservation result = reservationArgumentCaptor.getValue();

        Assert.assertNotNull(result);
        assertEquals(user2, result.getUser());
        assertEquals(appointment, result.getAppointment());
        assertEquals(ReservationStatus.PENDING, result.getStatus());

    }



//    @Test
//    public void getAllReservations(){
//        Pageable pageable = null;
//
//        reservationService.getAllReservations(null, 3L);
//    }

    @Test
    public void acceptReservationTest() throws NotFoundException {

        reservationService.acceptReservation(2L);

        then(reservationRepository).should().save(reservationArgumentCaptor.capture());

        Reservation result = reservationArgumentCaptor.getValue();


        Assert.assertNotNull(result);
        assertEquals(ReservationStatus.ACCEPTED, result.getStatus());
    }


    @Test
    public void cancelReservationTest() {

        try {
            reservationService.cancelReservation(3L);

            then(reservationRepository).should().save(reservationArgumentCaptor.capture());

            Reservation result = reservationArgumentCaptor.getValue();


            Assert.assertNotNull(result);
            assertEquals(ReservationStatus.CANCELED, result.getStatus());
        }catch (NotFoundException notFoundException){
            System.out.println("user not found");
        }

    }




}
