package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.Reservation;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;

import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Response.ApiResponse;
import edu.miu.cs.cs544.appointment.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentServiceImp implements AppointmentService{
    @Autowired
  private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment( CreateAppointment createAppointment) {
          Appointment appointment = new Appointment(
              createAppointment.getStartTime(),
              createAppointment.getEndTime(),
              createAppointment.getDuration(),
              createAppointment.getLocation(),
              createAppointment.getCategory()
               //   ,
              //createAppointment.getProvider()
          );

          return   appointmentRepository.save(appointment);


    }

    @Override
    public ApiResponse updateAppointment(CreateAppointment createAppointment ,Long id) {
        try {

            Appointment appointment = appointmentRepository.getById(id);

           if(appointment!=null){
               appointment.setStartTime(createAppointment.getStartTime());
               appointment.setEndTime(createAppointment.getEndTime());
               appointment.setDuration(createAppointment.getDuration());
               appointment.setLocation(createAppointment.getLocation());

               Appointment appointment2 =appointmentRepository.save(appointment);
               System.out.println(appointment2);
               return   new ApiResponse(true,"we updated it") ;
           }


            return   new ApiResponse(false,"Appointment doesn't exist") ;



        }catch (Exception e){
            System.out.println(e);
            return   new ApiResponse(false,"error not pudated fix it") ;

        }

    }

    @Override
    public List<Reservation> allResevations(Long appointmentId) {
        Appointment appointment =appointmentRepository.getById(appointmentId);
        return appointment.getResevationList();
    }

    @Override
    public ApiResponse DeleteAppointment(Long appointmentId) {
        Appointment appointment= appointmentRepository.getById(appointmentId);
        appointmentRepository.delete(appointment);
        return new ApiResponse(true,"Deleted") ;
    }

    @Override
    public List<Reservation> allResevationsByStatus(Long appointmentId, String status) {
        return null;
    }
}
