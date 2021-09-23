package edu.miu.cs.cs544.appointment.Repositories;

import edu.miu.cs.cs544.appointment.Models.appointment.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
