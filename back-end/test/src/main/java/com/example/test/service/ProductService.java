package com.example.test.service;

import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(ProductDto productDto) {
        Product products = modelMapper.map(productDto, Product.class);
        products.setName(products.getName());
        products.setPrice(productDto.getPrice());
        return productRepository.save(products);
    }

    public Optional<Product> updateProduct(Long id, ProductDto productDto) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isEmpty()) {
            return productData;
        }
        Product products = productData.get();
        products.setName(productDto.getName());
        products.setPrice(productDto.getPrice());
        return Optional.of(productRepository.save(products));
    }

    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
