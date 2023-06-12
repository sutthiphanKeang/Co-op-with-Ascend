package com.example.test.resolver;

import com.example.test.exception.GlobalExceptionHandler;
import com.example.test.mapper.CategoryDto;
import com.example.test.mapper.ProductDto;
import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.service.CategoryService;
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
import java.util.UUID;

@Controller
@AllArgsConstructor
@SchemaMapping(typeName = "Category")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class CategoryResolver {

    @Autowired
    private CategoryService categoryService;

    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryService.getCategory();
    }

    @QueryMapping
    public Category getCategoryById(@Argument("id") UUID id) {
        return categoryService.getCategory(id);
    }

    @MutationMapping
    public Category createCategory(@Valid @Argument("invoiceId") long invoiceId, @Argument("productId") long productId, @Argument CategoryDto categoryDto) {
        return categoryService.createCategory(invoiceId, productId, categoryDto);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument("id") UUID id) {
        return categoryService.deleteCategory(id);
    }
}
