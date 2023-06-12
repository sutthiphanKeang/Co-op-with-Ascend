//package com.example.test.controller;
//
//import com.example.test.exception.GlobalExceptionHandler;
//import com.example.test.mapper.InvoiceDto;
//import com.example.test.model.Invoice;
//import com.example.test.service.InvoiceService;
//import javax.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
//public class InvoiceController {
//
//    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
//
//    @Autowired
//    InvoiceService invoiceService;
//
//    @GetMapping("/get-invoice")
//    public ResponseEntity<List<Invoice>> getAllInvoice() {
//        try {
//            List<Invoice> invoiceData = invoiceService.getInvoice();
//            if (invoiceData.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(invoiceData, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("An error occurred while getting all of the invoice: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/get-invoice/{id}")
//    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") long id) {
//        try {
//            Optional<Invoice> invoiceData = invoiceService.getInvoice(id);
//            if (invoiceData.isPresent()) {
//                return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//        } catch (Exception e) {
//            logger.error("An error occurred while getting the invoice: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/post-invoice/{useId}")
//    public ResponseEntity<Invoice> createInvoice(@Valid @PathVariable("useId") Long userId) {
//        try {
//            Invoice invoicePost = invoiceService.createInvoice(userId);
//            if (invoicePost != null) {
//                return new ResponseEntity<>(invoicePost, HttpStatus.CREATED);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            logger.error("An error occurred while create the invoice: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/put-invoice/{id}")
//    public ResponseEntity<Invoice> updateInvoice(@Valid @PathVariable("id") long id, @RequestBody InvoiceDto invoiceDto) {
//        try {
//            Invoice invoicePut = invoiceService.updateInvoice(id, invoiceDto);
//            if (ObjectUtils.isEmpty(invoicePut)) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                return new ResponseEntity<>(invoicePut, HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            logger.error("An error occurred while update the invoice: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/delete-invoice/{id}")
//    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
//        try {
//            if (invoiceService.deleteInvoice(id)) {
//                return new ResponseEntity<>(HttpStatus.GONE);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            logger.error("An error occurred while delete the invoice: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
