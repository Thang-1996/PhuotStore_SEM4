package com.example.phuotstore.repository;

import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Boolean existsByProductName(String productName);

    Boolean existsByProductCode(String productCode);

    @Query("SELECT p FROM Product p WHERE p.productID = ?1")
    Product findByID(int productID);

    @Query("SELECT p FROM Product p WHERE p.productID = ?1")
    Optional<Product> findProductByID(int productID);

    @Query("SELECT p FROM Product p WHERE p.category.categoryID = ?1")
    Page<Product> findProductsByCategoryID(int categoryID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.brand.brandID = ?1")
    Page<Product> findProductsByBrandID(int brandID, Pageable pageable);

    @Query("SELECT p FROM Product p")
    Page<Product> getAllProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 'SHOW' ")
    Page<Product> findPaginateProductsStatusShow(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 'HIDDEN' ")
    Page<Product> findPaginateProductsStatusHidden(Pageable pageable);
}
