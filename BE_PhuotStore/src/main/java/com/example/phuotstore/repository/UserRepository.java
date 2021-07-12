package com.example.phuotstore.repository;

import com.example.phuotstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userID = ?1")
    Optional<User> findUserByID(int userID);

    @Query("SELECT u FROM User u")
    Page<User> getAllUsers(Pageable pageable);

}
