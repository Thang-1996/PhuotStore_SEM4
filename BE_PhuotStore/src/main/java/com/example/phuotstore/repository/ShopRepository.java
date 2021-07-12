package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @Query("SELECT s FROM Shop s WHERE s.shopID =?1")
    Optional<Shop> findShopByID(Integer shopID);

    @Query("SELECT s FROM Shop s WHERE s.status = 1 OR s.status = 2")
    Page<Shop> getAllBrands(Pageable pageable);

    @Query("SELECT s FROM Shop s WHERE s.status = 1 ")
    Page<Shop> findPaginateShopsStatusShow(Pageable pageable);

    @Query("SELECT s FROM Brand s WHERE s.status = 2 ")
    Page<Shop> findPaginateShopsStatusHidden(Pageable pageable);

    @Query("SELECT s FROM Shop s WHERE s.shopName = ?1")
    Shop findByShopName(String shopName);
}
