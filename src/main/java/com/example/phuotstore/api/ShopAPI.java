package com.example.phuotstore.api;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Shop;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "api/v1/shops")
public class ShopAPI {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Shop>> getAllShops(Pageable pageable) {
        return ResponseEntity.ok(shopRepository.getAllShops(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopByID(@PathVariable int id) {
        Optional<Shop> optionalShop = shopRepository.findShopByID(id);
        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalShop.get());
    }

//    @GetMapping("/hidden")
//    public ResponseEntity<Page<Shop>> getShopsByStatusHidden(Pageable pageable) {
//        return ResponseEntity.ok(shopRepository.findPaginateShopsStatusHidden(pageable));
//    }
//
//    @GetMapping("/show")
//    public ResponseEntity<Page<Shop>> getShopsByStatusShow(Pageable pageable) {
//        return ResponseEntity.ok(shopRepository.findPaginateShopsStatusShow(pageable));
//    }

    @PostMapping("/add")
    public ResponseEntity<?> createShop(@Valid @RequestBody Shop shop) {

        if (shopRepository.existsByShopName(shop.getShopName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Shop Name is already!"));
        }

        if (shopRepository.existsByAddress(shop.getAddress())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Address is already!"));
        }

        Shop shopSaved = shopRepository.save(shop);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(shop.getShopID())
            .toUri();
        return ResponseEntity.created(location).body(shopSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShop(@PathVariable int id,
                                        @Valid @RequestBody Shop shop) {

        Optional<Shop> optionalShop = shopRepository.findShopByID(id);

        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        shop.setShopID(optionalShop.get().getShopID());
        shopRepository.save(shop);
        return ResponseEntity.ok(optionalShop.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Shop> deleteShop(@PathVariable int id) {
        Optional<Shop> optionalShop = shopRepository.findShopByID(id);

        if (!optionalShop.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        shopRepository.delete(optionalShop.get());
        return ResponseEntity.noContent().build();
    }


}
