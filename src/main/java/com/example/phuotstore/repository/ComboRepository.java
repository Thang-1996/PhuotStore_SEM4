package com.example.phuotstore.repository;

import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ComboRepository extends JpaRepository<Combo, Integer> {

    Boolean existsByComboName(String comboName);

    Boolean existsByComboCode(String comboCode);

    @Query("SELECT p FROM Combo p WHERE p.comboID = ?1")
    Combo findByID(int comboID);

    @Query("SELECT cb FROM Combo cb WHERE cb.comboID = ?1")
    Optional<Combo> findComboByID(Integer comboID);

    @Query("SELECT cb FROM Combo cb WHERE cb.status = 'SHOW' OR cb.status = 'HIDDEN'")
    Page<Combo> getAllCombos(Pageable pageable);

    @Query("SELECT cb FROM Combo cb WHERE cb.status = 'SHOW' ")
    Page<Combo> findPaginateCombosStatusShow(Pageable pageable);

    @Query("SELECT cb FROM Combo cb WHERE cb.status = 'HIDDEN' ")
    Page<Combo> findPaginateCombosStatusHidden(Pageable pageable);

}
