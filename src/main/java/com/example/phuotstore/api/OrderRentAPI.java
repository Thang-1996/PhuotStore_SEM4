package com.example.phuotstore.api;


import com.example.phuotstore.dto.OrderRentDTO;
import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.OrderRent;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.model.User;
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

@CrossOrigin(origins = "*", maxAge = 3600)
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

//    @GetMapping("/status/waiting")
//    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusWaiting(Pageable pageable) {
//        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsWaiting(pageable));
//    }
//
//    @GetMapping("/status/confirmed")
//    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusConfirmed(Pageable pageable) {
//        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsConfirmed(pageable));
//    }
//
//    @GetMapping("/status/shipping")
//    public ResponseEntity<Page<OrderRent>> getOrderRentByStatusShipping(Pageable pageable) {
//        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsShipping(pageable));
//    }
//
//    @GetMapping("/status/complete")
//    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusComplete(Pageable pageable) {
//        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsComplete(pageable));
//    }
//
//    @GetMapping("/status/cancelled")
//    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusCancelled(Pageable pageable) {
//        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsCancelled(pageable));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderRent(@PathVariable int id,
                                             @Valid @RequestBody OrderRentDTO orderRentDTO) {

        Optional<OrderRent> optionalOrderRent = orderRentRepository.findOrderRentByID(id);
        if (!optionalOrderRent.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        OrderRent orderRent = new OrderRent(orderRentDTO.getOrderRentName(), orderRentDTO.getNote(), orderRentDTO.getBookingDate(), orderRentDTO.getRentalStart(), orderRentDTO.getRentalEnd(), orderRentDTO.getStatus(), orderRentDTO.getTotalQuantity(), orderRentDTO.getTotalPrice());

        Set<Integer> productID = orderRentDTO.getProduct();
        Set<Integer> comboID = orderRentDTO.getCombo();
        Set<Integer> userID = orderRentDTO.getUser();

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
