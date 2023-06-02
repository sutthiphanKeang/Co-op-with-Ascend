package com.example.test.controller;

import com.example.test.exception.GlobalExceptionHandler;
import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping("/get-product")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List<Product> productData = productService.getProduct();
            if (productData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while getting all of the product: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        try {
            Optional<Product> productData = productService.getProduct(id);
            if (productData.isPresent()) {
                return new ResponseEntity<>(productData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while getting the product: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post-product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            Product productPost = productService.createProduct(productDto);
            return new ResponseEntity<>(productPost, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("An error occurred while create the product: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/put-product/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @PathVariable("id") long id, @RequestBody ProductDto productDto) {
        try {
            Optional<Product> productPut = productService.updateProduct(id, productDto);
            if (productPut.isPresent()) {
                return new ResponseEntity<>(productPut.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while update the product: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            if (productService.deleteProduct(id)) {
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while delete the product: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
