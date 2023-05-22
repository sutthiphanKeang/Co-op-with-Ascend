package com.example.test.controller;

import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Spy
    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProduct() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productService.getProduct()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProduct();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(products, response.getBody());

        verify(productService, times(1)).getProduct();
    }

    @Test
    void testGetAllProduct_NoContent() {
        when(productService.getProduct()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Product>> response = productController.getAllProduct();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(productService, times(1)).getProduct();
    }

    @Test
    void testGetProductById() {
        long productId = 1L;
        Product product = new Product();

        when(productService.getProduct(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(productId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(product, response.getBody());

        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    void testGetProductById_NotFound() {
        long productId = 1L;

        when(productService.getProduct(productId)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(productId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();

        when(productService.createProduct(productDto)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(productDto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(product, response.getBody());

        verify(productService, times(1)).createProduct(productDto);
    }

    @Test
    void testUpdateProduct() {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        Product product = new Product();

        when(productService.updateProduct(productId, productDto)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.updateProduct(productId, productDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(product, response.getBody());

        verify(productService, times(1)).updateProduct(productId, productDto);
    }

    @Test
    void testUpdateProduct_NotFound() {
        long productId = 1L;
        ProductDto productDto = new ProductDto();

        when(productService.updateProduct(productId, productDto)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(productId, productDto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(productService, times(1)).updateProduct(productId, productDto);
    }

    @Test
    void testDeleteProduct() {
        long productId = 1L;

        when(productService.deleteProduct(productId)).thenReturn(true);

        ResponseEntity<HttpStatus> response = productController.deleteProduct(productId);

        Assertions.assertEquals(HttpStatus.GONE, response.getStatusCode());

        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testDeleteProduct_NotFound() {
        long productId = 1L;

        when(productService.deleteProduct(productId)).thenReturn(false);

        ResponseEntity<HttpStatus> response = productController.deleteProduct(productId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(productService, times(1)).deleteProduct(productId);
    }
}
