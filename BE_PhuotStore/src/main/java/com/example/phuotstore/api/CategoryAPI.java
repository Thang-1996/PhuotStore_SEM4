package com.example.phuotstore.api;


import com.example.phuotstore.model.Category;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryAPI {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryRepository.getAllCategories(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryByID(@PathVariable int id) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByID(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }

//    @GetMapping("/hidden")
//    public ResponseEntity<Page<Category>> getCategoriesByStatusHidden(Pageable pageable) {
//        return ResponseEntity.ok(categoryRepository.findPaginateCategoriesStatusHidden(pageable));
//    }
//
//    @GetMapping("/show")
//    public ResponseEntity<Page<Category>> getCategoriesByStatusShow(Pageable pageable) {
//        return ResponseEntity.ok(categoryRepository.findPaginateCategoriesStatusShow(pageable));
//    }

    @PostMapping("/add")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category) {

        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Category Name is already!"));
        }

        if (categoryRepository.existsByCategoryCode(category.getCategoryCode())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Category Code is already!"));
        }

        Category categorySaved = categoryRepository.save(category);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(category.getCategoryID())
            .toUri();
        return ResponseEntity.created(location).body(categorySaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id,
                                            @Valid @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByID(id);

        if (!optionalCategory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        category.setCategoryID(optionalCategory.get().getCategoryID());
        categoryRepository.save(category);
        return ResponseEntity.ok(optionalCategory.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByID(id);

        if (!optionalCategory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        categoryRepository.delete(optionalCategory.get());
        return ResponseEntity.noContent().build();
    }
}
