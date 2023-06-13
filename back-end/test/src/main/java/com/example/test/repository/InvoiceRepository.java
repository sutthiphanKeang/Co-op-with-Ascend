package com.example.test.repository;
import com.example.test.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    List<Invoice> findByUserId(Long id);
    List<Invoice> findByUserEmail(String email);
}
