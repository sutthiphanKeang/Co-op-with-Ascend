package com.example.test.controller;

import com.example.test.exceptions.GlobalExceptionHandler;
import com.example.test.mapper.CategoryDto;
import com.example.test.model.Category;
import com.example.test.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("/get-category")
    public ResponseEntity<List<Category>> getAllCategory() {
        try {
            if (categoryService.getCategory().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryService.getCategory(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while getting all of the category: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") UUID id) {
        try {
            Optional<Category> categoryData = categoryService.getCategory(id);
            if (categoryData.isPresent()) {
                return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error("An error occurred while getting the category: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post-category/{invoiceId}/{productId}")
    public ResponseEntity<Category> createCategory(@Valid @PathVariable("invoiceId") long invoiceId, @PathVariable("productId") long productId, @RequestBody CategoryDto categoryDto) {
        try {
            Category categoryPost = categoryService.createCategory(invoiceId, productId, categoryDto);
            if (categoryPost != null) {
                return new ResponseEntity<>(categoryPost, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while create the category: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") UUID id) {
        try {
            if (categoryService.deleteCategory(id)) {
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while delete the category: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
