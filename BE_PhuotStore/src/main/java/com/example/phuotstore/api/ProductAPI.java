package com.example.phuotstore.api;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.BrandRepository;
import com.example.phuotstore.repository.CategoryRepository;
import com.example.phuotstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/products")
public class ProductAPI {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ProductAPI(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping //read data
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productRepository.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable int id) {
        Optional<Product> optionalProduct = productRepository.findProductByID(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Page<Product>> getProductByBrandID(@PathVariable int id, Pageable pageable) {
        Optional<Brand> optionalBrand = brandRepository.findBrandByID(id);
        if (!optionalBrand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(productRepository.findProductsByBrandID(id, pageable));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Page<Product>> getProductByCategoryID(@PathVariable int id, Pageable pageable) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByID(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(productRepository.findProductsByCategoryID(id, pageable));
    }

    @GetMapping("/hidden")
    public ResponseEntity<Page<Product>> getProductsByStatusHidden(Pageable pageable) {
        return ResponseEntity.ok(productRepository.findPaginateProductsStatusHidden(pageable));
    }

    @GetMapping("/show")
    public ResponseEntity<Page<Product>> getProductsByStatusShow(Pageable pageable) {
        return ResponseEntity.ok(productRepository.findPaginateProductsStatusShow(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {

        if (productRepository.existsByProductName(product.getProductName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Product Name is already!"));
        }

        if (productRepository.existsByProductCode(product.getProductCode())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Product Code is already!"));
        }

        Optional<Brand> optionalBrand = brandRepository.findBrandByID(product.getBrand().getBrandID());
        if (!optionalBrand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Category> optionalCategory = categoryRepository.findCategoryByID(product.getCategory().getCategoryID());
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        product.setCreateAt(new Date());
        product.setBrand(optionalBrand.get());
        product.setCategory(optionalCategory.get());
        Product productSaved = productRepository.save(product);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(product.getProductID())
            .toUri();
        return ResponseEntity.created(location).body(productSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {

//        if (productRepository.existsByProductName(product.getProductName())) {
//            return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("Error: Product Name is already!"));
//        }
//
//        if (productRepository.existsByProductCode(product.getProductCode())) {
//            return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("Error: Product Code is already!"));
//        }

        Optional<Brand> optionalBrand = brandRepository.findBrandByID(product.getBrand().getBrandID());
        if (!optionalBrand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Category> optionalCategory = categoryRepository.findCategoryByID(product.getCategory().getCategoryID());
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Product> optionalProduct = productRepository.findProductByID(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        product.setProductID(optionalProduct.get().getProductID());
        product.setCreateAt(optionalProduct.get().getCreateAt());
        product.setUpdateAt(new Date());
        product.setBrand(optionalBrand.get());
        product.setCategory(optionalCategory.get());

        productRepository.save(product);
        return ResponseEntity.ok(optionalProduct.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        Optional<Product> optionalProduct= productRepository.findProductByID(id);

        if(!optionalProduct.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        productRepository.delete(optionalProduct.get());
        return ResponseEntity.noContent().build();
    }
}
