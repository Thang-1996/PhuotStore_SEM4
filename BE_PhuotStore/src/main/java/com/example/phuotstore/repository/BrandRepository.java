package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Boolean existsByBrandName(String brandName);

    Boolean existsByBrandCode(String BrandCode);

    @Query("SELECT br FROM Brand br WHERE br.brandID = ?1")
    Optional<Brand> findBrandByID(Integer brandID);

    @Query("SELECT br FROM Brand br WHERE br.status = 'SHOW' OR br.status = 'HIDDEN'")
    Page<Brand> getAllBrands(Pageable pageable);

    @Query("SELECT br FROM Brand br WHERE br.status = 'SHOW' ")
    Page<Brand> findPaginateBrandsStatusShow(Pageable pageable);

    @Query("SELECT br FROM Brand br WHERE br.status = 'HIDDEN' ")
    Page<Brand> findPaginateBrandsStatusHidden(Pageable pageable);
}
