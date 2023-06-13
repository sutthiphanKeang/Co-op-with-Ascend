//package com.example.test.service;
//
//import com.example.test.exception.ExceptionResolver;
//import com.example.test.mapper.ProductDto;
//import com.example.test.model.Product;
//import com.example.test.repository.ProductRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private ProductService productService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetProduct() {
//        List<Product> expectedProducts = new ArrayList<>();
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("test");
//        product.setPrice(1234);
//        expectedProducts.add(product);
//
//        when(productRepository.findAll()).thenReturn(expectedProducts);
//
//        List<Product> actualProducts = productService.getProduct();
//
//        Assertions.assertEquals(expectedProducts, actualProducts);
//        verify(productRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetProductById() {
//        long productId = 1L;
//        Product expectedProduct = new Product();
//        expectedProduct.setId(1L);
//        expectedProduct.setName("test");
//        expectedProduct.setPrice(1234);
//
//        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));
//
//        Product actualProduct = productService.getProduct(productId);
//
//        when(productRepository.findById(productId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> productService.getProduct(productId));
//        Assertions.assertEquals(expectedProduct, actualProduct);
//        verify(productRepository, times(2)).findById(productId);
//    }
//
//    @Test
//    void testCreateProduct() {
//        ProductDto productDto = new ProductDto();
//        productDto.setName("test");
//        productDto.setPrice(1234);
//
//        Product expectedProduct = new Product();
//        expectedProduct.setId(1L);
//        expectedProduct.setName(productDto.getName());
//        expectedProduct.setPrice(productDto.getPrice());
//
//        when(modelMapper.map(productDto, Product.class)).thenReturn(expectedProduct);
//        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);
//
//        Product actualProduct = productService.createProduct(productDto);
//
//        Assertions.assertEquals(expectedProduct, actualProduct);
//        verify(modelMapper, times(1)).map(productDto, Product.class);
//        verify(productRepository, times(1)).save(expectedProduct);
//    }
//
//    @Test
//    void testUpdateProduct() {
//        long productId = 1L;
//        ProductDto productDto = new ProductDto();
//        productDto.setName("test");
//        productDto.setPrice(1234);
//
//        Product expectedProduct = new Product();
//        expectedProduct.setId(1L);
//        expectedProduct.setName(productDto.getName());
//        expectedProduct.setPrice(productDto.getPrice());
//
//
//        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));
//        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);
//
//        Product actualProduct = productService.updateProduct(productId, productDto);
//
//        when(productRepository.findById(productId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> productService.updateProduct(productId, productDto));
//        Assertions.assertEquals(expectedProduct, actualProduct);
//        verify(productRepository, times(2)).findById(productId);
//        verify(productRepository, times(1)).save(expectedProduct);
//    }
//
//    @Test
//    void testDeleteProduct() {
//        long productId = 1L;
//        Product expectedProducts = new Product();
//        expectedProducts.setId(1L);
//        expectedProducts.setName("test");
//        expectedProducts.setPrice(1234);
//
//        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProducts));
//
//        boolean resultTrue = productService.deleteProduct(productId);
//
//        when(productRepository.findById(productId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> productService.deleteProduct(productId));
//        Assertions.assertTrue(resultTrue);
//        verify(productRepository, times(1)).deleteById(productId);
//    }
//}
