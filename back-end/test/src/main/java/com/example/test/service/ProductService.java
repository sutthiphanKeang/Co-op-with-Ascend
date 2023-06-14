package com.example.test.service;

import com.example.test.exception.ExceptionResolver;
import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
    }

    public Product createProduct(ProductDto productDto) {
        Product products = modelMapper.map(productDto, Product.class);
        products.setName(products.getName());
        products.setPrice(productDto.getPrice());
        return productRepository.save(products);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product productData = productRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        productData.setName(productDto.getName());
        productData.setPrice(productDto.getPrice());
        return productRepository.save(productData);
    }

    public boolean deleteProduct(Long id) {
        Product productData = productRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        productRepository.deleteById(productData.getId());
        return true;
    }
}
