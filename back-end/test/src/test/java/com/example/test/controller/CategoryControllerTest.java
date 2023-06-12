//package com.example.test.controller;
//
//import com.example.test.mapper.CategoryDto;
//import com.example.test.model.Category;
//import com.example.test.model.Invoice;
//import com.example.test.model.Product;
//import com.example.test.service.CategoryService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//class CategoryControllerTest {
//
//    @Mock
//    private CategoryService categoryService;
//
//    @InjectMocks
//    private CategoryController categoryController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
//    }
//
//    private static String asJsonString(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    @Test
//    void testGetAllCategory() throws Exception {
//        List<Category> categories = new ArrayList<>();
//        UUID categoryId = UUID.randomUUID();
//        Product product = new Product();
//        Invoice invoice = new Invoice();
//        Category category = new Category();
//        category.setId(categoryId);
//        category.setInvoice(invoice);
//        category.setProduct(product);
//        category.setUnit(1);
//        categories.add(category);
//
//        when(categoryService.getCategory()).thenReturn(categories);
//
//        mockMvc.perform(get("/api/get-category"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(category.getId().toString()))
//                .andExpect(jsonPath("$[0].unit").value(category.getUnit()))
//                .andExpect(jsonPath("$[0].product").value(category.getProduct()))
//                .andExpect(jsonPath("$[0].invoice").value(category.getInvoice()));
//
//        categories.clear();
//        mockMvc.perform(get("/api/get-category"))
//                .andExpect(status().isNoContent());
//
//        when(categoryService.getCategory()).thenThrow(new RuntimeException());
//
//        mockMvc.perform(get("/api/get-category"))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$").doesNotExist());
//    }
//
//    @Test
//    void testGetCategoryById() throws Exception {
//        UUID categoryId = UUID.randomUUID();
//        Product product = new Product();
//        Invoice invoice = new Invoice();
//        Category category = new Category();
//        category.setId(categoryId);
//        category.setInvoice(invoice);
//        category.setProduct(product);
//        category.setUnit(1);
//
//        when(categoryService.getCategory(categoryId)).thenReturn(Optional.of(category));
//
//        mockMvc.perform(get("/api/get-category/{id}", categoryId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(category.getId().toString()))
//                .andExpect(jsonPath("$.unit").value(category.getUnit()))
//                .andExpect(jsonPath("$.product").value(category.getProduct()))
//                .andExpect(jsonPath("$.invoice").value(category.getInvoice()));
//
//        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/get-category/{id}", categoryId))
//                .andExpect(status().isNotFound());
//
//        when(categoryService.getCategory(categoryId)).thenThrow(new RuntimeException());
//
//        mockMvc.perform(get("/api/get-category/{id}", categoryId))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$").doesNotExist());
//    }
//
//    @Test
//    void testCreateCategory() throws Exception {
//        long invoiceId = 1L;
//        long productId = 2L;
//        UUID categoryId = UUID.randomUUID();
//        Product product = new Product();
//        Invoice invoice = new Invoice();
//        CategoryDto categoryDto = new CategoryDto();
//        categoryDto.setUnit(2);
//
//        Category category = new Category();
//        category.setId(categoryId);
//        category.setUnit(categoryDto.getUnit());
//        category.setInvoice(invoice);
//        category.setProduct(product);
//
//        when(categoryService.createCategory(invoiceId, productId, categoryDto)).thenReturn(category);
//
//        mockMvc.perform(multipart("/api/post-category/{invoiceId}/{productId}", invoiceId, productId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(categoryDto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(category.getId().toString()))
//                .andExpect(jsonPath("$.unit").value(category.getUnit()))
//                .andExpect(jsonPath("$.product").value(category.getProduct()))
//                .andExpect(jsonPath("$.invoice").value(category.getInvoice()));
//
//        when(categoryService.createCategory(invoiceId, productId, categoryDto)).thenReturn(null);
//
//        mockMvc.perform(multipart("/api/post-category/{invoiceId}/{productId}", invoiceId, productId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(categoryDto)))
//                .andExpect(status().isNotFound());
//
//        when(categoryService.createCategory(invoiceId, productId, categoryDto)).thenThrow(new RuntimeException());
//
//        mockMvc.perform(multipart("/api/post-category/{invoiceId}/{productId}", invoiceId, productId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(categoryDto)))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$").doesNotExist());
//    }
//
//    @Test
//    void testDeleteCategory() throws Exception {
//        UUID categoryId = UUID.randomUUID();
//
//        when(categoryService.deleteCategory(categoryId)).thenReturn(true);
//
//        mockMvc.perform(delete("/api/delete-category/{id}", categoryId))
//                .andExpect(status().isGone());
//
//        when(categoryService.deleteCategory(categoryId)).thenReturn(false);
//
//        mockMvc.perform(delete("/api/delete-category/{id}", categoryId))
//                .andExpect(status().isNotFound());
//
//        when(categoryService.deleteCategory(categoryId)).thenThrow(new RuntimeException());
//
//        mockMvc.perform(delete("/api/delete-category/{id}", categoryId))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$").doesNotExist());
//    }
//}
