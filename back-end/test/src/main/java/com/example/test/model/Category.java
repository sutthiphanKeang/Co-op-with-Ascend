package com.example.test.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category", schema = "dbo", catalog = "test")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;


    @ManyToOne()
    @JoinColumn(name = "product_id")
    private  Product product;

    @Column(name = "unit")
    private Integer unit;
}
