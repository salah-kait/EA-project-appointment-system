package edu.miu.cs.cs544.appointment.Controllers;

import edu.miu.cs.cs544.appointment.Payload.Requests.CreateCategory;
import edu.miu.cs.cs544.appointment.Services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/api/category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategory createCategory){
        try {
            return ResponseEntity.ok(categoryServiceImpl.createCategory(createCategory));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/api/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody CreateCategory createCategory ,@PathVariable Long id){
        try {
            return  ResponseEntity.ok(categoryServiceImpl.updateCategory(createCategory,id));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/api/category")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getAllCategories(){
        try {
            return  ResponseEntity.ok(categoryServiceImpl.getAllCategories());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


}
