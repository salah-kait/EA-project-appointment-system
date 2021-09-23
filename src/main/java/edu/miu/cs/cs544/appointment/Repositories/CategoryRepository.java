package edu.miu.cs.cs544.appointment.Repositories;

import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryTitle(String title);
}
