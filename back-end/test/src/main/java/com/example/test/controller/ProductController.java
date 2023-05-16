package com.example.test.controller;

import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.model.User;
import com.example.test.repository.ProductRepository;
import com.example.test.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            if (productService.getProduct().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productService.getProduct(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        try {
            Optional<Product> productData = productService.getProduct(id);

            if (productData.isPresent()) {
                return new ResponseEntity<>(productData.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        try {
            Product products = modelMapper.map(productDto, Product.class);
            products.setName(products.getName());
            products.setPrice(productDto.getPrice());
            Product productPost = productService.createProduct(products);
            return new ResponseEntity<>(productPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto) {
        try {
            Optional<Product> ProductData = productService.getProduct(id);

            if (ProductData.isPresent()) {
                Product products = ProductData.get();
                products.setName(productDto.getName());
                products.setPrice(productDto.getPrice());
                Optional<Product> productPut = productService.updateProduct(id,products);
                return new ResponseEntity<>(productPut.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            if (productService.deleteProduct(id)) {
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
