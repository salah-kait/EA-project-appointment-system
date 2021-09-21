package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Payload.Requests.SignUpRequest;
import edu.miu.cs.cs544.appointment.Security.CurrentUser;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import edu.miu.cs.cs544.appointment.Util.Email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {

   @Autowired
   EmailSender emailSender;


   @PostMapping("/test")
   @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
   public void addCompany(@Valid @RequestBody SignUpRequest signUpRequest, @CurrentUser UserPrincipal userPrincipal) {

   }

   @GetMapping("/sqs")
   @PreAuthorize("hasRole('PROVIDER')")
   public void addCompany( @CurrentUser UserPrincipal userPrincipal) {
      emailSender.sendSimpleMessage("salah.khudairat@gmail.com","this is subject","this is bodey");
   }


}
