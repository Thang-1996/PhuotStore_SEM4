package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Boolean existsByBrandName(String brandName);

    Boolean existsByBrandCode(String brandCode);

    @Query("SELECT br FROM Brand br WHERE br.brandID =?1")
    Optional<Brand> findBrandByID(Integer brandID);

    @Query("SELECT br FROM Brand br WHERE br.status = 1 OR br.status = 2")
    Page<Brand> getAllBrands(Pageable pageable);

    @Query("SELECT br FROM Brand br WHERE br.status = 1 ")
    Page<Brand> findPaginateBrandsStatusShow(Pageable pageable);

    @Query("SELECT br FROM Brand br WHERE br.status = 2 ")
    Page<Brand> findPaginateBrandsStatusHidden(Pageable pageable);

    @Query("SELECT br FROM Brand br WHERE br.brandName = ?1")
    Brand findByBrandName(String brandName);
}
