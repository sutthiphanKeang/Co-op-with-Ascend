package com.example.test.service;

import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProduct() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getProduct();

        Assertions.assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProduct(productId);

        Assertions.assertEquals(Optional.of(product), result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(productDto);

        Assertions.assertEquals(product, result);
        verify(modelMapper, times(1)).map(productDto, Product.class);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct() {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        Optional<Product> productData = Optional.of(product);
        when(productRepository.findById(productId)).thenReturn(productData);
        when(productRepository.save(product)).thenReturn(product);

        Optional<Product> result = productService.updateProduct(productId, productDto);

        Assertions.assertEquals(productData, result);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        long productId = 1L;
        doNothing().when(productRepository).deleteById(productId);

        boolean result = productService.deleteProduct(productId);

        Assertions.assertTrue(result);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testDeleteProduct_NotFound() {
        long productId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(productId);

        boolean result = productService.deleteProduct(productId);

        Assertions.assertFalse(result);
        verify(productRepository, times(1)).deleteById(productId);
    }
}
