package edu.miu.cs.cs544.appointment.Service;


import edu.miu.cs.cs544.appointment.Models.Role;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import edu.miu.cs.cs544.appointment.Models.enums.RoleName;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.CategoryRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Services.AppointmentServiceImp;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {

    @InjectMocks
    AppointmentServiceImp appointmentService;

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Captor
    ArgumentCaptor<Appointment> appointmentArgumentCaptor;


    Role role_provider, role_client;
    Category category, category2;
    User user1, user2, user3, user4;
    Appointment appointment;
    CreateAppointment createAppointment1, createAppointment2, createAppointment3, createAppointment4;

    Appointment appointment1, appointment2, appointment3, appointment4;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        role_provider = new Role(1L, RoleName.ROLE_PROVIDER);
        role_client = new Role(1L, RoleName.ROLE_CLIENT);
        category = new Category("TM", 60L);
        user1 = new User("test1", "test1", "test@mail.com", "1234");
        user1.setRoles(new HashSet<Role>(Arrays.asList(role_provider)));
        appointment = new Appointment(category, LocalDateTime.of(2021, 12, 1, 1, 32,3), LocalDateTime.of(2021, 12, 1, 3, 4, 5), 30L, "Berlington", user1);

        user2 = new User("test2", "test2", "test2@mail.com", "1234");
        user2.setRoles(new HashSet<Role>(Arrays.asList(role_client)));

        user3 = new User("test3", "test3", "test3@gmail.com", "1234");
        user4 = new User("test4", "test4", "test4@gmail.com", "1234");

        user3.setRoles(new HashSet<Role>(Arrays.asList(role_client)));
        user4.setRoles(new HashSet<Role>(Arrays.asList(role_client)));


        createAppointment1 = new CreateAppointment(LocalDateTime.now(), LocalDateTime.now(), 3L, "DC",1l);

        createAppointment2 = new CreateAppointment(LocalDateTime.now(), LocalDateTime.now(), 3L, "DC",1l);

        createAppointment3 = new CreateAppointment(LocalDateTime.now(), LocalDateTime.now(), 3L, "DC",1l);

        createAppointment4 = new CreateAppointment(LocalDateTime.now(), LocalDateTime.now(), 3L, "DC",1l);

        appointment1 = new Appointment(LocalDateTime.now(), LocalDateTime.now(),30L, "DC");

        appointment2 = new Appointment(LocalDateTime.now(), LocalDateTime.now(),15L, "DC");

        appointment3 = new Appointment(LocalDateTime.now(), LocalDateTime.now(),15L, "DC");

        appointment4 = new Appointment(LocalDateTime.now(), LocalDateTime.now(),15L, "DC");


        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));
        when(userRepository.findById(4L)).thenReturn(Optional.of(user4));

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment1));
        when(appointmentRepository.findById(2L)).thenReturn(Optional.of(appointment2));
        when(appointmentRepository.findById(3L)).thenReturn(Optional.of(appointment3));
        when(appointmentRepository.findById(4L)).thenReturn(Optional.of(appointment4));

        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));


    }

    @Test
    public void createAppointmentTest() throws NotFoundException {


        CreateAppointment createAppointment = new CreateAppointment(LocalDateTime.now(), LocalDateTime.now(), 3L, "DC",1L);


        appointmentService.createAppointment(createAppointment, 2L);

        then(appointmentRepository).should().save(appointmentArgumentCaptor.capture());

        Appointment result = appointmentArgumentCaptor.getValue();

        Assert.assertNotNull(result);
        assertEquals(user2, result.getProvider());
        assertEquals(createAppointment.getDuration(), result.getDuration());
        assertEquals(createAppointment.getLocation(), result.getLocation());

    }




}