package com.example.test.controller;

import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void testGetAllProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setPrice(1234);
        products.add(product);

        when(productService.getProduct()).thenReturn(products);

        mockMvc.perform(get("/api/get-product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(product.getId()))
                .andExpect(jsonPath("$[0].name").value(product.getName()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()));

        products.clear();
        mockMvc.perform(get("/api/get-product"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetProductById() throws Exception {
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("test");
        product.setPrice(1234);

        when(productService.getProduct(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/get-product/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()));

        when(productService.getProduct(productId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/get-product/{id}", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("test");
        productDto.setPrice(1234);

        Product product = new Product();
        product.setId(1L);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        when(productService.createProduct(any(ProductDto.class))).thenReturn(product);

        mockMvc.perform(post("/api/post-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    void testUpdateProduct() throws Exception {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setName("test");
        productDto.setPrice(5678);

        Product product = new Product();
        product.setId(productId);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        when(productService.updateProduct(eq(productId), any(ProductDto.class))).thenReturn(Optional.of(product));

        mockMvc.perform(put("/api/put-product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()));

        when(productService.updateProduct(eq(productId), any(ProductDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/put-product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteProduct() throws Exception {
        long productId = 1L;

        when(productService.deleteProduct(productId)).thenReturn(true);

        mockMvc.perform(delete("/api/delete-product/{id}", productId))
                .andExpect(status().isGone());

        when(productService.deleteProduct(productId)).thenReturn(false);

        mockMvc.perform(delete("/api/delete-product/{id}", productId))
                .andExpect(status().isNotFound());
    }
}
