package com.example.test.mapper;

import com.example.test.model.Category;
import com.example.test.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private User userId;
    private Set<Category> categories;
    private Boolean status;
    private Integer unit;

}