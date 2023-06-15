package com.example.test.model;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category", schema = "dbo", catalog = "system")
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private  Product product;

    @Column(name = "unit")
    private Integer unit;
}
