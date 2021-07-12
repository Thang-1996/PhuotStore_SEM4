package com.example.phuotstore.repository;

import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Boolean existsByOrderName(String orderName);

    @Query("SELECT cb FROM Order cb WHERE cb.orderID = ?1")
    Optional<Order> findOrderByID(Integer orderID);

    @Query("SELECT od FROM Order od ")
    Page<Order> getAllOrders(Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.user.userID = ?1")
    Page<Order> getOrdersByUserID(int userID, Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.status = 'WAITING' ORDER BY od.createAt ASC ")
    Page<Order> findPaginateOrderWaiting(Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.status = 'CONFIRM' ORDER BY od.updateAt ASC ")
    Page<Order> findPaginateOrderConfirmed(Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.status = 'SHIPPING' ORDER BY od.updateAt ASC ")
    Page<Order> findPaginateOrderShipping(Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.status = 'COMPLETE' ORDER BY od.updateAt ASC ")
    Page<Order> findPaginateOrderComplete(Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.status = 'CANCEL' ORDER BY od.updateAt ASC ")
    Page<Order> findPaginateOrderCancelled(Pageable pageable);

}
