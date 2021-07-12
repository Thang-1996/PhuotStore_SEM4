package com.example.phuotstore.api;


import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.payload.request.ComboRequest;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.ComboRepository;
import com.example.phuotstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/combos")
public class ComboAPI {
    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Combo>> getAllCombos(Pageable pageable) {
        return ResponseEntity.ok(comboRepository.getAllCombos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Combo> getComboByID(@PathVariable int id) {
        Optional<Combo> optionalCombo = comboRepository.findComboByID(id);
        if (!optionalCombo.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalCombo.get());
    }

    @GetMapping("/hidden")
    public ResponseEntity<Page<Combo>> getCombosByStatusHidden(Pageable pageable) {
        return ResponseEntity.ok(comboRepository.findPaginateCombosStatusHidden(pageable));
    }

    @GetMapping("/show")
    public ResponseEntity<Page<Combo>> getCombosByStatusShow(Pageable pageable) {
        return ResponseEntity.ok(comboRepository.findPaginateCombosStatusShow(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCombo(@Valid @RequestBody ComboRequest comboRequest) {
        if (comboRepository.existsByComboName(comboRequest.getComboName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Combo Name is already!"));
        }

        if (comboRepository.existsByComboCode(comboRequest.getComboCode())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Combo Code is already!"));
        }

        Combo combo = new Combo(comboRequest.getComboName(), comboRequest.getComboCode(), comboRequest.getComboDesc(), comboRequest.getDiscount(), comboRequest.getQuantity(), comboRequest.getStatus());

        Set<Integer> productID = comboRequest.getProduct();
        Set<Product> products = new HashSet<>();
        if (productID == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            productID.forEach((product) -> {
                Product productSaved = productRepository.findByID(product);
                products.add(productSaved);
            });
            combo.setProducts(products);
            comboRepository.save(combo);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(combo.getComboID())
                .toUri();
            return ResponseEntity.created(location).body(combo);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCombo(@PathVariable int id,
                                             @Valid @RequestBody ComboRequest comboRequest) {


        Optional<Combo> optionalCombo = comboRepository.findComboByID(id);

        if (!optionalCombo.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }


        Combo combo = new Combo(comboRequest.getComboName(), comboRequest.getComboCode(), comboRequest.getComboDesc(), comboRequest.getDiscount(),comboRequest.getQuantity(), comboRequest.getStatus());

        Set<Integer> productID = comboRequest.getProduct();
        Set<Product> products = new HashSet<>();
        if (productID == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            productID.forEach((product) -> {
                Product productSaved = productRepository.findByID(product);
                products.add(productSaved);
            });
            combo.setProducts(products);
            combo.setComboID(optionalCombo.get().getComboID());
            comboRepository.save(combo);
            return ResponseEntity.ok(optionalCombo.get());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Combo> deleteCombo(@PathVariable int id) {
        Optional<Combo> optionalCombo = comboRepository.findComboByID(id);

        if (!optionalCombo.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        comboRepository.delete(optionalCombo.get());
        return ResponseEntity.noContent().build();
    }
}
