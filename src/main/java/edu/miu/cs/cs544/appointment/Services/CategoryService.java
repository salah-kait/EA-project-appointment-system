package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Models.User;
import edu.miu.cs.cs544.appointment.Models.appointment.Appointment;
import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateAppointment;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateCategory;
import edu.miu.cs.cs544.appointment.Repositories.CategoryRepository;
import edu.miu.cs.cs544.appointment.Repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    public Category createCategory(CreateCategory createCategory) throws NotFoundException {

        Category category = new Category(createCategory.getTitle(),createCategory.getDefualtDuration());

        return   categoryRepository.save(category);
    }

    public Category updateCategory(CreateCategory createCategory) throws NotFoundException {

        Category category = new Category(createCategory.getTitle(),createCategory.getDefualtDuration());

        return   categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return   categoryRepository.findAll();
    }
}
