package com.example.phuotstore.api;

import com.example.phuotstore.dto.InventoryDTO;
import com.example.phuotstore.model.Inventory;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.model.Shop;
import com.example.phuotstore.repository.InventoryRepository;
import com.example.phuotstore.repository.ProductRepository;
import com.example.phuotstore.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<?> createInventory(@Valid @RequestBody InventoryDTO inventoryDTO) {

        Optional<Product> optionalProduct = productRepository.findProductByID(inventoryDTO.getProductID());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Shop> optionalShop = shopRepository.findShopByID(inventoryDTO.getShopID());
        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Inventory inventory = new Inventory(inventoryDTO.getQtyOnHand());

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
    public ResponseEntity<?> updateInventory(@PathVariable int id, @Valid @RequestBody InventoryDTO inventoryDTO) {

        Optional<Product> optionalProduct = productRepository.findProductByID(inventoryDTO.getProductID());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Shop> optionalShop = shopRepository.findShopByID(inventoryDTO.getShopID());
        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Inventory> optionalInventory = inventoryRepository.findInventoryByID(id);
        if (!optionalInventory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Inventory inventory = new Inventory(inventoryDTO.getQtyOnHand());

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
