package com.example.test.mapper;

import com.example.test.model.Invoice;
import com.example.test.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private UUID id;
    private Invoice invoice;
    private Product product;
}
