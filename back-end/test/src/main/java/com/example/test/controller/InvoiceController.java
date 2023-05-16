package com.example.test.controller;

import com.example.test.mapper.InvoiceDto;
import com.example.test.model.Invoice;
import com.example.test.model.User;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.UserRepository;
import com.example.test.service.InvoiceService;
import com.example.test.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/invoice")
    public ResponseEntity<List<Invoice>> getAllInvoice() {
        try {
            if (invoiceService.getInvoice().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(invoiceService.getInvoice(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") long id) {
        try {
            Optional<Invoice> invoiceData = invoiceService.getInvoice(id);
            if (invoiceData.isPresent()) {
                return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invoice/{useId}")
    public ResponseEntity<Invoice> createInvoice(@PathVariable("useId") Long userId) {
        try {
            Invoice invoicePost = invoiceService.createInvoice(userId);
            if(invoicePost != null){
                return new ResponseEntity<>(invoicePost, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/invoice/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody InvoiceDto invoiceDto) {
        try {
            Optional<Invoice> invoiceData = invoiceService.getInvoice(id);

            if (invoiceData.isPresent()) {
                invoiceData.get().setUnit(invoiceDto.getUnit());
                invoiceData.get().setStatus(invoiceDto.getStatus());
                Optional<Invoice> invoicePut = invoiceService.updateInvoice(id, invoiceData.get());
                return new ResponseEntity<>(invoicePut.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
        try {
            if (invoiceService.deleteInvoice(id)) {
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
