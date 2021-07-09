package com.example.phuotstore.api;


import com.example.phuotstore.model.Brand;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/brands")
public class BrandAPI {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Brand>> getAllBrands(Pageable pageable) {
        return ResponseEntity.ok(brandRepository.getAllBrands(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandByID(@PathVariable int id) {
        Optional<Brand> optionalBrand = brandRepository.findBrandByID(id);
        if (!optionalBrand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalBrand.get());
    }

    @GetMapping("/hidden")
    public ResponseEntity<Page<Brand>> getBrandsByStatusHidden(Pageable pageable) {
        return ResponseEntity.ok(brandRepository.findPaginateBrandsStatusHidden(pageable));
    }

    @GetMapping("/show")
    public ResponseEntity<Page<Brand>> getBrandsByStatusShow( Pageable pageable) {
        return ResponseEntity.ok(brandRepository.findPaginateBrandsStatusShow(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createBrand(@Valid @RequestBody Brand brand) {

        if (brandRepository.existsByBrandName(brand.getBrandName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Brand Name is already!"));
        }

        if (brandRepository.existsByBrandCode(brand.getBrandCode())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Brand Code is already!"));
        }

        Brand brandSaved = brandRepository.save(brand);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(brand.getBrandID())
            .toUri();
        return ResponseEntity.created(location).body(brandSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable int id,
                                             @Valid @RequestBody Brand brand){

        Optional<Brand> optionalBrand= brandRepository.findBrandByID(id);

        if(!optionalBrand.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        brand.setBrandID(optionalBrand.get().getBrandID());
        brandRepository.save(brand);
        return ResponseEntity.ok(optionalBrand.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable int id){
        Optional<Brand> optionalBrand= brandRepository.findBrandByID(id);

        if(!optionalBrand.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        brandRepository.delete(optionalBrand.get());
        return ResponseEntity.noContent().build();
    }
}
