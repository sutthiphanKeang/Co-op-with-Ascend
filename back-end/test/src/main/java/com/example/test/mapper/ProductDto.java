package com.example.test.mapper;

import com.example.test.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private Set<Category> categories;
    private String name;
    private Integer price;
}
