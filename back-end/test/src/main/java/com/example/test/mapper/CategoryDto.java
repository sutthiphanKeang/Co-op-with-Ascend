package com.example.test.mapper;

import com.example.test.model.Invoice;
import com.example.test.model.Product;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private UUID id;

    @NotNull(message = "Invoice cannot be null")
    private Invoice invoice;

    @NotNull(message = "Product cannot be null")
    private Product product;

    @Positive(message = "Unit must be a positive number")
    @NotNull(message = "Unit cannot be null")
    private Integer unit;
}
