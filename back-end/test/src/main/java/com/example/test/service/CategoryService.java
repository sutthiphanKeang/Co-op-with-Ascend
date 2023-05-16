package com.example.test.service;

import com.example.test.model.Category;
import com.example.test.model.Invoice;
import com.example.test.model.Product;
import com.example.test.model.User;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.ProductRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Category> getCategory(){
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> getCategory(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Long invoiceId, Long productId, Category category) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if(product.isEmpty() || invoice.isEmpty()){
            return null;
        }
        category.setProduct(product.get());
        category.setInvoice(invoice.get());
        return categoryRepository.save(category);
    }

    public boolean deleteCategory(UUID id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
