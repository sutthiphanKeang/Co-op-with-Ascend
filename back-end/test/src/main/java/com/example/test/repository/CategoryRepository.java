package com.example.test.repository;
import com.example.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{
    List<Category> findByProduct(Long product_id);
    List<Category> findByInvoice(Long invoice_id);
}
