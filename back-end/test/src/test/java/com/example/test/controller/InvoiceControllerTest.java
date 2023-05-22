package com.example.test.controller;

import com.example.test.mapper.InvoiceDto;
import com.example.test.model.Invoice;
import com.example.test.service.InvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceControllerTest {

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInvoice() {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice());

        when(invoiceService.getInvoice()).thenReturn(invoices);

        ResponseEntity<List<Invoice>> response = invoiceController.getAllInvoice();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(invoices, response.getBody());

        verify(invoiceService, times(1)).getInvoice();
    }

    @Test
    void testGetAllInvoice_NoContent() {
        when(invoiceService.getInvoice()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Invoice>> response = invoiceController.getAllInvoice();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(invoiceService, times(1)).getInvoice();
    }

    @Test
    void testGetInvoiceById() {
        long invoiceId = 1L;
        Invoice invoice = new Invoice();

        when(invoiceService.getInvoice(invoiceId)).thenReturn(Optional.of(invoice));

        ResponseEntity<Invoice> response = invoiceController.getInvoiceById(invoiceId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(invoice, response.getBody());

        verify(invoiceService, times(1)).getInvoice(invoiceId);
    }

    @Test
    void testGetInvoiceById_NotFound() {
        long invoiceId = 1L;

        when(invoiceService.getInvoice(invoiceId)).thenReturn(Optional.empty());

        ResponseEntity<Invoice> response = invoiceController.getInvoiceById(invoiceId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(invoiceService, times(1)).getInvoice(invoiceId);
    }

    @Test
    void testCreateInvoice() {
        long userId = 1L;
        Invoice invoice = new Invoice();

        when(invoiceService.createInvoice(userId)).thenReturn(invoice);

        ResponseEntity<Invoice> response = invoiceController.createInvoice(userId);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(invoice, response.getBody());

        verify(invoiceService, times(1)).createInvoice(userId);
    }

    @Test
    void testCreateInvoice_NotFound() {
        long userId = 1L;

        when(invoiceService.createInvoice(userId)).thenReturn(null);

        ResponseEntity<Invoice> response = invoiceController.createInvoice(userId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(invoiceService, times(1)).createInvoice(userId);
    }

    @Test
    void testUpdateInvoice() {
        long invoiceId = 1L;
        InvoiceDto invoiceDto = new InvoiceDto();
        Invoice invoice = new Invoice();

        when(invoiceService.updateInvoice(invoiceId, invoiceDto)).thenReturn(invoice);

        ResponseEntity<Invoice> response = invoiceController.updateInvoice(invoiceId, invoiceDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(invoice, response.getBody());

        verify(invoiceService, times(1)).updateInvoice(invoiceId, invoiceDto);
    }

    @Test
    void testUpdateInvoice_NotFound() {
        long invoiceId = 1L;
        InvoiceDto invoiceDto = new InvoiceDto();

        when(invoiceService.updateInvoice(invoiceId, invoiceDto)).thenReturn(null);

        ResponseEntity<Invoice> response = invoiceController.updateInvoice(invoiceId, invoiceDto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(invoiceService, times(1)).updateInvoice(invoiceId, invoiceDto);
    }

    @Test
    void testDeleteInvoice() {
        long invoiceId = 1L;

        when(invoiceService.deleteInvoice(invoiceId)).thenReturn(true);

        ResponseEntity<HttpStatus> response = invoiceController.deleteInvoice(invoiceId);

        Assertions.assertEquals(HttpStatus.GONE, response.getStatusCode());

        verify(invoiceService, times(1)).deleteInvoice(invoiceId);
    }

    @Test
    void testDeleteInvoice_NotFound() {
        long invoiceId = 1L;

        when(invoiceService.deleteInvoice(invoiceId)).thenReturn(false);

        ResponseEntity<HttpStatus> response = invoiceController.deleteInvoice(invoiceId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(invoiceService, times(1)).deleteInvoice(invoiceId);
    }
}
