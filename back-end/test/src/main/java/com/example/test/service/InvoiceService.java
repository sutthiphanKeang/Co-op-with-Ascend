package com.example.test.service;

import com.example.test.exception.ExceptionResolver;
import com.example.test.mapper.InvoiceDto;
import com.example.test.model.Invoice;
import com.example.test.model.User;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Invoice> getInvoice() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
    }

    public List<Invoice> getInvoice(long  id) {
        List<Invoice> invoiceData = invoiceRepository.findByUserId(id);
        if(ObjectUtils.isEmpty(invoiceData)){
            throw new ExceptionResolver.NotFoundException("ID: " + id + " Not Found.");
        }
        return invoiceData;
    }

    public List<Invoice> getInvoiceEmail(String  email) {
        List<Invoice> invoiceData = invoiceRepository.findByUserEmail(email);
        if(ObjectUtils.isEmpty(invoiceData)){
            throw new ExceptionResolver.NotFoundException("Email: " + email + " Not Found.");
        }
        return invoiceData;
    }

    public Invoice createInvoice(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ExceptionResolver.NotFoundException("User ID: " + userId + " Not Found."));
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setStatus(false);
        return invoiceRepository.save(invoice);
    }

    public Invoice createInvoice(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ExceptionResolver.NotFoundException("User Email: " + email + " Not Found."));
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setStatus(false);
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, InvoiceDto invoiceDto) {
        Invoice invoiceData = invoiceRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        invoiceData.setStatus(invoiceDto.getStatus());
        return invoiceRepository.save(invoiceData);
    }

    public boolean deleteInvoice(Long id) {
        Invoice invoiceData = invoiceRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        invoiceRepository.deleteById(invoiceData.getId());
        return true;
        }
}
