package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Exception.BadRequestException;
import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import edu.miu.cs.cs544.appointment.Models.reservation.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;

import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import edu.miu.cs.cs544.appointment.Repositories.CategoryRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

        Appointment appointment = appointmentRepository.getById(id);

        if (appointment != null) {
            appointment.setStartTime(createAppointment.getStartTime());
            appointment.setEndTime(createAppointment.getEndTime());
            appointment.setLocation(createAppointment.getLocation());
        } else {
            new NotFoundException("appointment not found");
        }

        Category category = categoryRepository.findById(createAppointment.getCategoryId()).orElseThrow(() ->
                new NotFoundException("category not found")
        );

        if (createAppointment.getDuration() != null) {
            appointment.setDuration(createAppointment.getDuration());
        } else {
            appointment.setDuration(category.getDefualtDuration());
        }

        // check if the appointment is the user's
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal) authentication.getPrincipal();
        if (appointment.getProvider().getId() != currentUser.getId()) {
            throw new BadRequestException("unauthorized access detected");

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
    public ApiResponse DeleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        if (appointment != null) {
            appointmentRepository.delete(appointment);
            return new ApiResponse(true, "Deleted");
        }
        return new ApiResponse(false, "Not found");
    }

    @Override
    public List<Reservation> allResevationsByStatus(Long appointmentId, String status) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointements() {
        return appointmentRepository.findAll();
    }
}
