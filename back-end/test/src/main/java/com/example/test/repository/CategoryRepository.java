package com.example.test.repository;
import com.example.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{
    List<Category> findByProductId(Long productId);
    List<Category> findByInvoiceId(Long invoiceId);
}
