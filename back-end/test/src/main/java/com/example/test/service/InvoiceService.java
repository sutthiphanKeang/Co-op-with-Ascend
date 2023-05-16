package com.example.test.service;

import com.example.test.model.Invoice;
import com.example.test.model.User;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Invoice> getInvoice(){
        return (List<Invoice>) invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice, Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if(user.isEmpty()){
            return null;
        }
        invoice.setUser(user.get());
        return invoiceRepository.save(invoice);

    }

    public Optional<Invoice> updateInvoice(Long id, Invoice invoice) {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);
        if(invoiceData.isEmpty()) {
            return null;
        }
        return Optional.of(invoiceRepository.save(invoice));
    }

    public boolean deleteInvoice(Long id) {
        try {
            invoiceRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
