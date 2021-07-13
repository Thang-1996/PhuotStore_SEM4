package com.example.phuotstore.api;

import com.example.phuotstore.model.*;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/inventories")
public class InventoryAPI {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final InventoryRepository inventoryRepository;

    public InventoryAPI(ProductRepository productRepository, ShopRepository shopRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping //read data
    public ResponseEntity<Page<Inventory>> getAllInventories(Pageable pageable) {
        return ResponseEntity.ok(inventoryRepository.getAllInventories(pageable));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Page<Inventory>> getInventoriesByProductID(@PathVariable int id, Pageable pageable) {
        Optional<Product> optionalProduct = productRepository.findProductByID(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(inventoryRepository.findInventoriesByProductID(id, pageable));
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<Page<Inventory>> getInventoriesByShopID(@PathVariable int id, Pageable pageable) {
        Optional<Shop> optionalShop = shopRepository.findShopByID(id);
        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(inventoryRepository.findInventoriesByShopID(id, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createInventory(@Valid @RequestBody Inventory inventory) {

        Optional<Product> optionalProduct = productRepository.findProductByID(inventory.getProduct().getProductID());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Shop> optionalShop = shopRepository.findShopByID(inventory.getShop().getShopID());
        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        inventory.setProduct(optionalProduct.get());
        inventory.setShop(optionalShop.get());
        Inventory inventorySaved = inventoryRepository.save(inventory);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(inventory.getInventoryID())
            .toUri();
        return ResponseEntity.created(location).body(inventorySaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable int id, @Valid @RequestBody Inventory inventory) {

        Optional<Product> optionalProduct = productRepository.findProductByID(inventory.getProduct().getProductID());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Shop> optionalShop = shopRepository.findShopByID(inventory.getShop().getShopID());
        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }


        Optional<Inventory> optionalInventory = inventoryRepository.findInventoryByID(id);
        if (!optionalInventory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        inventory.setInventoryID(optionalInventory.get().getInventoryID());
        inventory.setProduct(optionalProduct.get());
        inventory.setShop(optionalShop.get());

        inventoryRepository.save(inventory);
        return ResponseEntity.ok(optionalProduct.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable int id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findInventoryByID(id);

        if (!optionalInventory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        inventoryRepository.delete(optionalInventory.get());
        return ResponseEntity.noContent().build();
    }


}
