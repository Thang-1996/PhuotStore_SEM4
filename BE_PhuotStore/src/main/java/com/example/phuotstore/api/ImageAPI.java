package com.example.phuotstore.api;


import com.example.phuotstore.model.Image;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.repository.ImageRepository;
import com.example.phuotstore.repository.ProductRepository;
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
@RequestMapping(path = "api/v1/images")
public class ImageAPI {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Image>> getAllImages(Pageable pageable) {
        return ResponseEntity.ok(imageRepository.getAllImages(pageable));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Page<Image>> getImageByProductID(@PathVariable int id, Pageable pageable) {
        Optional<Product> optionalProduct = productRepository.findProductByID(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(imageRepository.findImagesByProductID(id, pageable));
    }

    @GetMapping("/hidden")
    public ResponseEntity<Page<Image>> getImagesByStatusHidden(Pageable pageable) {
        return ResponseEntity.ok(imageRepository.findPaginateImagesStatusHidden(pageable));
    }

    @GetMapping("/show")
    public ResponseEntity<Page<Image>> getImagesByStatusShow(Pageable pageable) {
        return ResponseEntity.ok(imageRepository.findPaginateImagesStatusShow(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createImage(@Valid @RequestBody Image image) {

        Optional<Product> optionalProduct = productRepository.findProductByID(image.getProduct().getProductID());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        image.setProduct(optionalProduct.get());

        Image imageSaved = imageRepository.save(image);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(image.getImgID())
            .toUri();
        return ResponseEntity.created(location).body(imageSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateImage(@PathVariable int id, @Valid @RequestBody Image image) {

        Optional<Product> optionalProduct = productRepository.findProductByID(image.getProduct().getProductID());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Image> optionalImage = imageRepository.findImageByID(id);
        if (!optionalImage.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        image.setImgID(optionalImage.get().getImgID());
        image.setProduct(optionalProduct.get());

        imageRepository.save(image);
        return ResponseEntity.ok(optionalImage.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable int id) {
        Optional<Image> optionalImage = imageRepository.findImageByID(id);

        if (!optionalImage.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        imageRepository.delete(optionalImage.get());
        return ResponseEntity.noContent().build();
    }

}
