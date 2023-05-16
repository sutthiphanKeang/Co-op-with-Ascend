package com.example.test.controller;
import com.example.test.model.Category;
import com.example.test.model.Invoice;
import com.example.test.model.Product;
import com.example.test.model.User;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.ProductRepository;
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
    CategoryRepository CategoryRepository;

    @Autowired
    ProductRepository ProductRepository;

    @Autowired
    InvoiceRepository InvoiceRepository;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) UUID id) {
        try {
            List<Category> categories = new ArrayList<Category>();

            if (id == null)
                CategoryRepository.findAll().forEach(categories::add);
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        try {
            List<Category> CategoryData1 = CategoryRepository.findByInvoice(id);
            List<Category> CategoryData2 = CategoryRepository.findByProduct(id);
            if (!CategoryData1.isEmpty()) {
                return new ResponseEntity<>(CategoryData1.get(0), HttpStatus.OK);
            } else if (!CategoryData2.isEmpty()) {
                return new ResponseEntity<>(CategoryData2.get(0), HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category NewCategory) {
        try {
            Optional<Invoice> invoice = InvoiceRepository.findById(NewCategory.getInvoice().getId());
            Optional<Product> product = ProductRepository.findById(NewCategory.getProduct().getId());
            if(!invoice.isEmpty() && !product.isEmpty()){
                Category category = CategoryRepository.save(NewCategory);
                return new ResponseEntity<>(category, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/category/{id}")
//    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category putCategory) {
//        try {
//            Optional<Category> CategoryData = CategoryRepository.findById(id);
//
//            if (CategoryData.isPresent()) {
//                Category category = CategoryData.get();
//                category.setProduct(putCategory.getProduct().getId());
//                return new ResponseEntity<>(CategoryRepository.save(putCategory), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") UUID id) {
        try {
            Optional<Category> CategoryData = CategoryRepository.findById(id);
            if (CategoryData.isPresent()) {
                CategoryRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/category/all")
    public ResponseEntity<HttpStatus> deleteAllCategory() {
        try {
            CategoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
