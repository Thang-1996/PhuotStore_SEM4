package com.example.phuotstore.repository;

import com.example.phuotstore.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.inventoryID = ?1")
    Inventory findByID(int inventoryID);

    @Query("SELECT i FROM Inventory i WHERE i.inventoryID = ?1")
    Optional<Inventory> findInventoryByID(int inventoryID);

    @Query("SELECT i FROM Inventory i WHERE i.product.productID = ?1")
    Page<Inventory> findInventoriesByProductID(int productID, Pageable pageable);

    @Query("SELECT i FROM Inventory i WHERE i.shop.shopID = ?1")
    Page<Inventory> findInventoriesByShopID(int brandID, Pageable pageable);

    @Query("SELECT i FROM Inventory i")
    Page<Inventory> getAllInventories(Pageable pageable);



}
