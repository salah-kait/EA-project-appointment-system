package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Models.Role;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.enums.RoleName;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Security.CurrentUser;
import edu.miu.cs.cs544.appointment.Services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentServiceImp;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createAppointment() {
        CreateAppointment createAppointment = new CreateAppointment();
        createAppointment.setDuration(20l);
        createAppointment.setEndTime(LocalDateTime.of(2021, Month.DECEMBER,20,11,11));
        createAppointment.setCategoryId(1l);
        createAppointment.setEndTime(LocalDateTime.of(2021, Month.DECEMBER,21,11,11));
        User user = new User();
        user.setEmail("bettyFilli@gmail.com");
        user.setId(1l);
        user.setName("Betty");
        user.setPassword("1234");
        Role role = new Role();
        role.setId(1l);
        role.setName(RoleName.ROLE_ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        try {

        }catch(Exception e){

        }
//        when(appointmentServiceImp.createAppointment(createAppointment,user.getId()))
//                .thenReturn(new Point(11, 11));
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void allResevations() {
    }

    @Test
    void deleteAppointment() {
    }

    @Test
    void getAllAppointements() {
    }
}