package com.example.phuotstore.api;


import com.example.phuotstore.dto.OrderDTO;
import com.example.phuotstore.dto.OrderRentDTO;
import com.example.phuotstore.model.*;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.ComboRepository;
import com.example.phuotstore.repository.OrderRentRepository;
import com.example.phuotstore.repository.ProductRepository;
import com.example.phuotstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
      @GetMapping("/{id}")
    public ResponseEntity<OrderRent> getOrderRentByID(@PathVariable int id) {
        Optional<OrderRent> optionalOrderRent = orderRentRepository.findOrderRentByID(id);
        if (!optionalOrderRent.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalOrderRent.get());
    }

    @GetMapping("/paynow")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByPayNow(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsPayNow(pageable));
    }

    @GetMapping("/online")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByOnline(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsOnline(pageable));
    }

    @GetMapping("/waiting")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusWaiting(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsWaiting(pageable));
    }

    @GetMapping("/confirm")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusConfirm(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsConfirm(pageable));
    }

    @GetMapping("/shipping")
    public ResponseEntity<Page<OrderRent>> getOrderRentByStatusShipping(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsShipping(pageable));
    }

    @GetMapping("/done")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusDone(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsDone(pageable));
    }

    @GetMapping("/cancel")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusCancel(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsCancel(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOrderRent(@Valid @RequestBody OrderRentDTO orderRentDTO) {
        if (comboRepository.existsByComboName(orderRentDTO.getOrderRentName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Order Rent Name is already!"));
        }

        Optional<User> optionalUser = userRepository.findUserByID(orderRentDTO.getUserID());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        OrderRent orderRent = new OrderRent(orderRentDTO.getOrderRentName(), orderRentDTO.getNote(), orderRentDTO.getStatus(), orderRentDTO.getTotalQuantity(), orderRentDTO.getTotalPrice(), orderRentDTO.getTotalRental(), orderRentDTO.getDeposits(), orderRentDTO.getBookingDate(), orderRentDTO.getRentalStart(), orderRentDTO.getRentalEnd(), orderRentDTO.getFirstName(), orderRentDTO.getLastName(), orderRentDTO.getEmail(), orderRentDTO.getShippingAddress(), orderRentDTO.getPhone(), orderRentDTO.getPaymentType());

        Set<Integer> productID = orderRentDTO.getProduct();
        Set<Integer> comboID = orderRentDTO.getCombo();

        Set<Product> products = new HashSet<>();
        Set<Combo> combos = new HashSet<>();
        if (productID == null && comboID == null) {
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

            orderRent.setCreateAt(new Date());
            orderRent.setProducts(products);
            orderRent.setUser(optionalUser.get());
            orderRent.setCombos(combos);
            orderRentRepository.save(orderRent);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderRent.getOrderRentID())
                .toUri();
            return ResponseEntity.created(location).body(orderRent);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderRent(@PathVariable int id,
                                             @Valid @RequestBody OrderRentDTO orderRentDTO) {

        Optional<OrderRent> optionalOrderRent = orderRentRepository.findOrderRentByID(id);
        if (!optionalOrderRent.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<User> optionalUser = userRepository.findUserByID(orderRentDTO.getUserID());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        OrderRent orderRent = new OrderRent(orderRentDTO.getOrderRentName(), orderRentDTO.getNote(), orderRentDTO.getStatus(), orderRentDTO.getTotalQuantity(), orderRentDTO.getTotalPrice(), orderRentDTO.getTotalRental(), orderRentDTO.getDeposits(), orderRentDTO.getBookingDate(), orderRentDTO.getRentalStart(), orderRentDTO.getRentalEnd(), orderRentDTO.getFirstName(), orderRentDTO.getLastName(), orderRentDTO.getEmail(), orderRentDTO.getShippingAddress(), orderRentDTO.getPhone(), orderRentDTO.getPaymentType());

        Set<Integer> productID = orderRentDTO.getProduct();
        Set<Integer> comboID = orderRentDTO.getCombo();
        Integer userID = orderRentDTO.getUserID();

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
            orderRent.setUser(optionalUser.get());
            orderRentRepository.save(orderRent);
            return ResponseEntity.ok(optionalOrderRent.get());
        }
    }
}
