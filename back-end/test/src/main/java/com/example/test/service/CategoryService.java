package com.example.test.service;

import com.example.test.mapper.CategoryDto;
import com.example.test.model.Category;
import com.example.test.model.Invoice;
import com.example.test.model.Product;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Long invoiceId, Long productId, CategoryDto categoryDto) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (product.isEmpty() || invoice.isEmpty()) {
            return null;
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setUnit(categoryDto.getUnit());
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
