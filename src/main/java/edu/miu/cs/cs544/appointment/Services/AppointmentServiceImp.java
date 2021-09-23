package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Exception.BadRequestException;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;

import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.CategoryRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import edu.miu.cs.cs544.appointment.Services.interfaces.AppointmentService;
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
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Appointment createAppointment(CreateAppointment createAppointment, Long id) throws NotFoundException {
        Appointment appointment = new Appointment(
                createAppointment.getStartTime(),
                createAppointment.getEndTime(),
                createAppointment.getDuration(),
                createAppointment.getLocation()
        );

        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found")
        );

        Category category = categoryRepository.findById(createAppointment.getCategoryId()).orElseThrow(() ->
                new NotFoundException("catagory not found")
        );


        if (createAppointment.getDuration() != null) {
            appointment.setDuration(createAppointment.getDuration());
        } else {
            appointment.setDuration(category.getDefualtDuration());
        }

        appointment.setCategory(category);
        appointment.setProvider(user);

        return appointmentRepository.save(appointment);


    }

    @Override
    public Appointment updateAppointment(CreateAppointment createAppointment, Long id) throws NotFoundException {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Appointment not found")
        );

        checkOwnerUserEligibility(appointment);

        appointment.setStartTime(createAppointment.getStartTime());
        appointment.setEndTime(createAppointment.getEndTime());
        appointment.setLocation(createAppointment.getLocation());

        Category category = categoryRepository.findById(createAppointment.getCategoryId()).orElseThrow(() ->
                new NotFoundException("category not found")
        );

        if (createAppointment.getDuration() != null) {
            appointment.setDuration(createAppointment.getDuration());
        } else {
            appointment.setDuration(category.getDefualtDuration());
        }

        appointment.setCategory(category);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Reservation> allResevations(Long appointmentId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        return appointment.getResevationList();
    }

    @Override
    public Boolean DeleteAppointment(Long appointmentId) throws NotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NotFoundException("Appointment not found")
        );

        checkOwnerUserEligibility(appointment);
        appointmentRepository.delete(appointment);
        return true;
    }

    @Override
    public List<Reservation> allResevationsByStatus(Long appointmentId, String status) {
        return null;
    }

    @Override
    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public List<Appointment> findAppointmentStartAtDateTime(LocalDateTime startAt1,LocalDateTime startAt2) {
        return appointmentRepository.findAppointmentStartAtDateTime(startAt1,startAt2);
    }


    public void checkOwnerUserEligibility(Appointment appointment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)authentication.getPrincipal();

        Boolean isAdmin = currentUser.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin &&  (appointment.getProvider().getId() != currentUser.getId()) ){
            throw new BadRequestException("unauthorized access detected");
        }
    }
}
