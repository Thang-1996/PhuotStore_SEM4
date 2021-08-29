package com.example.phuotstore.repository;

import com.example.phuotstore.model.OrderRent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRentRepository extends JpaRepository<OrderRent, Integer> {

    @Query("SELECT od FROM OrderRent od WHERE od.orderRentID = ?1")
    Optional<OrderRent> findOrderRentByID(Integer orderRentID);

    @Query("SELECT od FROM OrderRent od ")
    Page<OrderRent> getAllOrderRents(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.user.userID = ?1")
    Page<OrderRent> getOrderRentsByUserID(int userID, Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.paymentType = 'PAYNOW' ORDER BY od.createAt ASC ")
    Page<OrderRent> findPaginateOrderRentsPayNow(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.paymentType = 'ONLINE' ORDER BY od.createAt ASC ")
    Page<OrderRent> findPaginateOrderRentsOnline(Pageable pageable);


    @Query("SELECT od FROM OrderRent od WHERE od.status = 'WAITING' ORDER BY od.createAt ASC ")
    Page<OrderRent> findPaginateOrderRentsWaiting(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 'COMFIRM' ORDER BY od.createAt ASC ")
    Page<OrderRent> findPaginateOrderRentsConfirm(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 'SHIPPING' ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsShipping(Pageable pageable);
    
    @Query("SELECT od FROM OrderRent od WHERE od.status = 'EXTEND' ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsExtend(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 'EXPIRED' ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsExpired(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 'DONE' ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsDone(Pageable pageable);

    @Query("SELECT od FROM OrderRent od WHERE od.status = 'CANCEL' ORDER BY od.updateAt ASC ")
    Page<OrderRent> findPaginateOrderRentsCancel(Pageable pageable);
}
