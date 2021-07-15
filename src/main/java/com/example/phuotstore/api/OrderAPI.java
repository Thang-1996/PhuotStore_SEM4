package com.example.phuotstore.api;

import com.example.phuotstore.dto.OrderDTO;
import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Order;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.model.User;
import com.example.phuotstore.repository.ComboRepository;
import com.example.phuotstore.repository.OrderRepository;
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
@RequestMapping(path = "api/v1/orders")
public class OrderAPI {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComboRepository comboRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.getAllOrders(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable int id) {
        Optional<Order> optionalOrder = orderRepository.findOrderByID(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalOrder.get());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Order>> getOrdersByUserID(@PathVariable int id, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findUserByID(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(orderRepository.getOrdersByUserID(id, pageable));
    }

//    @GetMapping("/status/waiting")
//    public ResponseEntity<Page<Order>> getOrdersByStatusWaiting(Pageable pageable) {
//        return ResponseEntity.ok(orderRepository.findPaginateOrderWaiting(pageable));
//    }
//
//    @GetMapping("/status/confirmed")
//    public ResponseEntity<Page<Order>> getOrdersByStatusConfirmed(Pageable pageable) {
//        return ResponseEntity.ok(orderRepository.findPaginateOrderConfirmed(pageable));
//    }
//
//    @GetMapping("/status/shipping")
//    public ResponseEntity<Page<Order>> getOrdersByStatusShipping(Pageable pageable) {
//        return ResponseEntity.ok(orderRepository.findPaginateOrderShipping(pageable));
//    }
//
//    @GetMapping("/status/complete")
//    public ResponseEntity<Page<Order>> getOrdersByStatusComplete(Pageable pageable) {
//        return ResponseEntity.ok(orderRepository.findPaginateOrderComplete(pageable));
//    }
//
//    @GetMapping("/status/cancelled")
//    public ResponseEntity<Page<Order>> getOrdersByStatusCancelled(Pageable pageable) {
//        return ResponseEntity.ok(orderRepository.findPaginateOrderCancelled(pageable));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id,
                                         @Valid @RequestBody OrderDTO orderDTO) {

        Optional<Order> optionalOrder = orderRepository.findOrderByID(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Order order = new Order(orderDTO.getOrderName(), orderDTO.getNote(), orderDTO.getStatus(), orderDTO.getTotalQuantity(), orderDTO.getTotalPrice());

        Set<Integer> productID = orderDTO.getProduct();
        Set<Integer> comboID = orderDTO.getCombo();
        Integer userID = orderDTO.getUserID();

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

            order.setProducts(products);
            order.setCombos(combos);
            order.setUpdateAt(new Date());
            order.setOrderID(optionalOrder.get().getOrderID());
            orderRepository.save(order);
            return ResponseEntity.ok(optionalOrder.get());
        }
    }
}
