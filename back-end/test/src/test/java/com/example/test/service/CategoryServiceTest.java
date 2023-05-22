package com.example.test.service;

import com.example.test.mapper.CategoryDto;
import com.example.test.model.Category;
import com.example.test.model.Invoice;
import com.example.test.model.Product;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategory() {
        Category category1 = new Category();
        Category category2 = new Category();
        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

        List<Category> result = categoryService.getCategory();

        Assertions.assertEquals(List.of(category1, category2), result);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById() {
        UUID categoryId = UUID.randomUUID();
        Category category = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategory(categoryId);

        Assertions.assertEquals(Optional.of(category), result);
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void testCreateCategory() {
        Long invoiceId = 1L;
        Long productId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        Product product = new Product();
        Invoice invoice = new Invoice();
        Category category = new Category();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.createCategory(invoiceId, productId, categoryDto);

        Assertions.assertEquals(category, result);
        verify(productRepository, times(1)).findById(productId);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testCreateCategory_ProductNotFound() {
        Long invoiceId = 1L;
        Long productId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Category result = categoryService.createCategory(invoiceId, productId, categoryDto);

        Assertions.assertNull(result);
        verify(productRepository, times(1)).findById(productId);
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void testCreateCategory_InvoiceNotFound() {
        Long invoiceId = 1L;
        Long productId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        Category result = categoryService.createCategory(invoiceId, productId, categoryDto);

        Assertions.assertNull(result);
        verify(productRepository, times(1)).findById(productId);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void testDeleteCategory() {
        UUID categoryId = UUID.randomUUID();
        doNothing().when(categoryRepository).deleteById(categoryId);

        boolean result = categoryService.deleteCategory(categoryId);

        Assertions.assertTrue(result);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void testDeleteCategory_NotFound() {
        UUID categoryId = UUID.randomUUID();
        doThrow(EmptyResultDataAccessException.class).when(categoryRepository).deleteById(categoryId);

        boolean result = categoryService.deleteCategory(categoryId);

        Assertions.assertFalse(result);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}

