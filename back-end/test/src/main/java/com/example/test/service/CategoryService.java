package com.example.test.service;

import com.example.test.exception.ExceptionResolver;
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
import org.springframework.util.ObjectUtils;

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

    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
    }

    public Category createCategory(Long invoiceId, Long productId, CategoryDto categoryDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ExceptionResolver.NotFoundException("Product ID: " + productId + " Not Found."));
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ExceptionResolver.NotFoundException("Invoice ID: " + invoiceId + " Not Found."));
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setUnit(categoryDto.getUnit());
        category.setProduct(product);
        category.setInvoice(invoice);
        return categoryRepository.save(category);
    }

    public boolean deleteCategory(UUID id) {
        Category categoryData = categoryRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        categoryRepository.deleteById(categoryData.getId());
        return true;

    }
}
