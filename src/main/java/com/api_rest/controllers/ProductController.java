package com.api_rest.controllers;

import com.api_rest.ProductRepository;
import com.api_rest.error.ApiError;
import com.api_rest.models.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
@Validated
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Product product){
            Product newProduct = productRepository.save(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody Product product){

        if(!productRepository.existsById(id)){
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        Product updateProduct = productRepository.save(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        if(!productRepository.existsById(id)){
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
