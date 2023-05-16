package com.example.test.controller;
import com.example.test.model.Category;
import com.example.test.model.Invoice;
import com.example.test.model.Product;
import com.example.test.model.User;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.ProductRepository;
import com.example.test.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        try {
            if (categoryService.getCategory().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryService.getCategory(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") UUID id) {
        try {
            Optional<Category> categoryData = categoryService.getCategory(id);
            if (categoryData.isPresent()) {
                return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category/{invoiceId}/{productId}")
    public ResponseEntity<Category> createCategory(@PathVariable("invoiceId") long invoiceId, @PathVariable("productId") long productId) {
        try {
            Category categoryPost = categoryService.createCategory(invoiceId, productId);
            if(categoryPost != null){
                return new ResponseEntity<>(invoicePost, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") UUID id) {
        try {
            if (categoryService.deleteCategory(id)) {
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
