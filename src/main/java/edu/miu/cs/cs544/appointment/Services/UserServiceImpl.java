package edu.miu.cs.cs544.appointment.Services;


import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import edu.miu.cs.cs544.appointment.Services.interfaces.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found"));
    }
}
