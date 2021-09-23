package edu.miu.cs.cs544.appointment.Services.interfaces;

import edu.miu.cs.cs544.appointment.Models.User;
import javassist.NotFoundException;

public interface UserService {

    public User save(User user);

    public User getUserById(Long userId) throws NotFoundException ;
}
