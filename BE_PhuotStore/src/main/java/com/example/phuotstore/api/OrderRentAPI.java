package com.example.phuotstore.api;


import com.example.phuotstore.model.*;
import com.example.phuotstore.payload.request.OrderRentRequest;
import com.example.phuotstore.repository.ComboRepository;
import com.example.phuotstore.repository.OrderRentRepository;
import com.example.phuotstore.repository.ProductRepository;
import com.example.phuotstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/orderRents")
public class OrderRentAPI {

    @Autowired
    private OrderRentRepository orderRentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComboRepository comboRepository;

    @GetMapping //read data
    public ResponseEntity<Page<OrderRent>> getAllOrderRents(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.getAllOrderRents(pageable));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByUserID(@PathVariable int id, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findUserByID(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(orderRentRepository.getOrderRentsByUserID(id, pageable));
    }

    @GetMapping("/status/waiting")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusWaiting(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsWaiting(pageable));
    }

    @GetMapping("/status/confirmed")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusConfirmed(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsConfirmed(pageable));
    }

    @GetMapping("/status/shipping")
    public ResponseEntity<Page<OrderRent>> getOrderRentByStatusShipping(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsShipping(pageable));
    }

    @GetMapping("/status/complete")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusComplete(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsComplete(pageable));
    }

    @GetMapping("/status/cancelled")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusCancelled(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsCancelled(pageable));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderRent(@PathVariable int id,
                                             @Valid @RequestBody OrderRentRequest orderRentRequest) {

        Optional<OrderRent> optionalOrderRent = orderRentRepository.findOrderRentByID(id);
        if (!optionalOrderRent.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        OrderRent orderRent = new OrderRent(orderRentRequest.getOrderRentName(), orderRentRequest.getNote(), orderRentRequest.getCreateAt(), orderRentRequest.getUpdateAt(), orderRentRequest.getRentalStart(), orderRentRequest.getRentalEnd(), orderRentRequest.getStatus(), orderRentRequest.getQuantity());

        Set<Integer> productID = orderRentRequest.getProduct();
        Set<Integer> comboID = orderRentRequest.getCombo();
        Set<Integer> userID = orderRentRequest.getUser();

        Set<Product> products = new HashSet<>();
        Set<Combo> combos = new HashSet<>();
        if (userID == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            productID.forEach((product) -> {
                Product productSaved = productRepository.findByID(product);
                products.add(productSaved);
            });

            comboID.forEach((combo) -> {
                Combo comboSaved = comboRepository.findByID(combo);
                combos.add(comboSaved);
            });

            orderRent.setProducts(products);
            orderRent.setCombos(combos);
            orderRent.setUpdateAt(new Date());
            orderRent.setOrderRentID(optionalOrderRent.get().getOrderRentID());
            orderRentRepository.save(orderRent);
            return ResponseEntity.ok(optionalOrderRent.get());
        }
    }

}
