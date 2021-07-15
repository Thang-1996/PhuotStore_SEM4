package com.example.phuotstore.repository;

import com.example.phuotstore.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByCategoryName(String categoryName);

    Boolean existsByCategoryCode(String categoryCode);

    @Query("SELECT ca FROM Category ca WHERE ca.categoryID = ?1")
    Optional<Category> findCategoryByID(Integer categoryID);

    @Query("SELECT ca FROM Category ca")
    Page<Category> getAllCategories(Pageable pageable);

//    @Query("SELECT ca FROM Category ca WHERE ca.status = 'SHOW' ")
//    Page<Category> findPaginateCategoriesStatusShow(Pageable pageable);
//
//    @Query("SELECT ca FROM Category ca WHERE ca.status = 'HIDDEN' ")
//    Page<Category> findPaginateCategoriesStatusHidden(Pageable pageable);

}
