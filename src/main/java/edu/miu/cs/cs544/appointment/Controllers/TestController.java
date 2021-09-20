package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Payload.Requests.SignUpRequest;
import edu.miu.cs.cs544.appointment.Security.CurrentUser;
import edu.miu.cs.cs544.appointment.Security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {

   @PostMapping("/test")
   @PreAuthorize("hasRole('ADMIN') OR hasRole('PROVIDER')")
   public void addCompany(@Valid @RequestBody SignUpRequest signUpRequest, @CurrentUser UserPrincipal userPrincipal) {

   }


}
