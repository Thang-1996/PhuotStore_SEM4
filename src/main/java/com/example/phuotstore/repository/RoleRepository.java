package com.example.phuotstore.repository;

import com.example.phuotstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Optional<Role> findByRoleName(String roleName);

}
