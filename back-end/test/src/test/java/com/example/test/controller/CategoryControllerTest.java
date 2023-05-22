package com.example.test.controller;

import com.example.test.mapper.CategoryDto;
import com.example.test.model.Category;
import com.example.test.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        when(categoryService.getCategory()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategory();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(categories, response.getBody());

        verify(categoryService, times(1)).getCategory();
    }

    @Test
    void testGetAllCategory_NoContent() {
        when(categoryService.getCategory()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Category>> response = categoryController.getAllCategory();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(categoryService, times(1)).getCategory();
    }

    @Test
    void testGetCategoryById() {
        UUID categoryId = UUID.randomUUID();
        Category category = new Category();

        when(categoryService.getCategory(categoryId)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(category, response.getBody());

        verify(categoryService, times(1)).getCategory(categoryId);
    }

    @Test
    void testGetCategoryById_NotFound() {
        UUID categoryId = UUID.randomUUID();

        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(categoryService, times(1)).getCategory(categoryId);
    }

    @Test
    void testCreateCategory() {
        long invoiceId = 1L;
        long productId = 2L;
        CategoryDto categoryDto = new CategoryDto();
        Category category = new Category();

        when(categoryService.createCategory(invoiceId, productId, categoryDto)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(invoiceId, productId, categoryDto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(category, response.getBody());

        verify(categoryService, times(1)).createCategory(invoiceId, productId, categoryDto);
    }

    @Test
    void testCreateCategory_NotFound() {
        long invoiceId = 1L;
        long productId = 2L;
        CategoryDto categoryDto = new CategoryDto();

        when(categoryService.createCategory(invoiceId, productId, categoryDto)).thenReturn(null);

        ResponseEntity<Category> response = categoryController.createCategory(invoiceId, productId, categoryDto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(categoryService, times(1)).createCategory(invoiceId, productId, categoryDto);
    }

    @Test
    void testDeleteCategory() {
        UUID categoryId = UUID.randomUUID();

        when(categoryService.deleteCategory(categoryId)).thenReturn(true);

        ResponseEntity<HttpStatus> response = categoryController.deleteCategory(categoryId);

        Assertions.assertEquals(HttpStatus.GONE, response.getStatusCode());

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    void testDeleteCategory_NotFound() {
        UUID categoryId = UUID.randomUUID();

        when(categoryService.deleteCategory(categoryId)).thenReturn(false);

        ResponseEntity<HttpStatus> response = categoryController.deleteCategory(categoryId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }
}
