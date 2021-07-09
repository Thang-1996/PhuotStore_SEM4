package com.example.phuotstore.repository;

import com.example.phuotstore.model.Order;
import com.example.phuotstore.model.OrderRent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRentRepository extends JpaRepository<OrderRent, Integer> {
    Boolean existsByOrderRentName(String orderRentName);

    @Query("SELECT od FROM OrderRent od WHERE od.orderRentID = ?1")
    Optional<OrderRent> findOrderRentByID(Integer orderRentID);

    @Query("SELECT od FROM OrderRent od ")
    Page<OrderRent> getAllOrderRents(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.user.userID = ?1")
    Page<OrderRent> getOrderRentsByUserID(int userID, Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 1 ORDER BY od.createAt ASC ")
    Page<OrderRent> findPaginateOrderRentsWaiting(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 2 ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsConfirmed(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 3 ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsShipping(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 4 ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsComplete(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 5 ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsCancelled(Pageable pageable);
}
