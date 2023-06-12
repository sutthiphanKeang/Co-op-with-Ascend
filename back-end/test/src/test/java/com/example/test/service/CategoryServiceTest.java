package com.example.test.service;

import com.example.test.exception.ExceptionResolver;
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

import java.util.ArrayList;
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
        List<Category> expectedCategories = new ArrayList<>();
        UUID categoryId = UUID.randomUUID();
        Product product = new Product();
        Invoice invoice = new Invoice();
        Category category = new Category();
        category.setId(categoryId);
        category.setInvoice(invoice);
        category.setProduct(product);
        category.setUnit(1);
        expectedCategories.add(category);

        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = categoryService.getCategory();

        Assertions.assertEquals(expectedCategories, actualCategories);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById() {
        UUID categoryId = UUID.randomUUID();
        Product product = new Product();
        Invoice invoice = new Invoice();
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        expectedCategory.setInvoice(invoice);
        expectedCategory.setProduct(product);
        expectedCategory.setUnit(1);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.getCategory(categoryId);

        when(categoryRepository.findById(categoryId)).thenThrow(ExceptionResolver.NotFoundException.class);

        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> categoryService.getCategory(categoryId));
        Assertions.assertEquals(expectedCategory, actualCategory);
        verify(categoryRepository, times(2)).findById(categoryId);
    }

    @Test
    void testCreateCategory() {
        long invoiceId = 1L;
        long productId = 2L;
        UUID categoryId = UUID.randomUUID();
        Product product = new Product();
        Invoice invoice = new Invoice();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUnit(2);

        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        expectedCategory.setUnit(categoryDto.getUnit());
        expectedCategory.setInvoice(invoice);
        expectedCategory.setProduct(product);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(expectedCategory);
        when(categoryRepository.save(any(Category.class))).thenReturn(expectedCategory);

        Category actualCategory = categoryService.createCategory(invoiceId, productId, categoryDto);

        Assertions.assertEquals(expectedCategory, actualCategory);
        verify(productRepository, times(1)).findById(productId);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(categoryRepository, times(1)).save(expectedCategory);
    }

//    @Test
//    void testCreateCategory_ProductNotFound() {
//        Long invoiceId = 1L;
//        Long productId = 1L;
//        CategoryDto categoryDto = new CategoryDto();
//        Invoice invoice = new Invoice();
//        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
//        when(productRepository.findById(productId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> categoryService.createCategory(invoiceId, productId, categoryDto));
//        verify(productRepository, times(1)).findById(productId);
//        verify(invoiceRepository, times(1)).findById(invoiceId);
//        verifyNoInteractions(categoryRepository);
//    }

    @Test
    void testCreateCategory_InvoiceNotFound() {
        Long invoiceId = 1L;
        Long productId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceRepository.findById(invoiceId)).thenThrow(ExceptionResolver.NotFoundException.class);

        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> categoryService.createCategory(invoiceId, productId, categoryDto));
        verify(productRepository, times(1)).findById(productId);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void testDeleteCategory() {
        UUID categoryId = UUID.randomUUID();
        Category expectedCategory = new Category();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        boolean resultTrue = categoryService.deleteCategory(categoryId);

        when(categoryRepository.findById(categoryId)).thenThrow(ExceptionResolver.NotFoundException.class);

        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> categoryService.deleteCategory(categoryId));
        Assertions.assertTrue(resultTrue);
    }
}

