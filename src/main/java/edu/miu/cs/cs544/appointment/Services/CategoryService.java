package edu.miu.cs.cs544.appointment.Services;

import edu.miu.cs.cs544.appointment.Exception.BadRequestException;
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
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    public Category createCategory(CreateCategory createCategory) throws NotFoundException {

        Category category = new Category(createCategory.getTitle(),createCategory.getDefualtDuration());

        return   categoryRepository.save(category);
    }

    public Category updateCategory(CreateCategory createCategory,Long id) throws NotFoundException {

        Category category = categoryRepository.getById(id);

        if(category!=null){
           category.setTitle(createCategory.getTitle());
           category.setDefualtDuration(createCategory.getDefualtDuration());
        }

        return   categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return   categoryRepository.findAll();
    }

    public Category getCategoryByTitle(String title){
        Optional<Category> category = categoryRepository.findByTitle(title);

        if(category.isPresent()){
            return category.get();
        }else {
            throw  new BadRequestException("category not found");
        }
    }
}
