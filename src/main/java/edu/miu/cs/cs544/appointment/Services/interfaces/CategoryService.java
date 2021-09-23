package edu.miu.cs.cs544.appointment.Services.interfaces;

import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import edu.miu.cs.cs544.appointment.Payload.Requests.CreateCategory;
import javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    public Category createCategory(CreateCategory createCategory) throws NotFoundException;

    public Category updateCategory(CreateCategory createCategory,Long id) throws NotFoundException;

    public List<Category> getAllCategories();
}
