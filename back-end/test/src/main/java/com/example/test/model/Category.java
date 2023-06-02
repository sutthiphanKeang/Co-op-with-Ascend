package com.example.test.model;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category", schema = "dbo", catalog = "test")
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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
