package com.example.test.resolver;

import com.example.test.exception.GlobalExceptionHandler;
import com.example.test.mapper.ProductDto;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@SchemaMapping(typeName = "Product")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class ProductResolver {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getProduct();
    }

    @QueryMapping
    public Product getProductById(@Argument("id") long id) {
        return productService.getProduct(id);
    }

    @MutationMapping
    public Product createProduct(@Valid @Argument ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @MutationMapping
    public Product updateProduct(@Valid @Argument("id") long id, @Argument ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument("id") long id) {
        return productService.deleteProduct(id);
    }
}
