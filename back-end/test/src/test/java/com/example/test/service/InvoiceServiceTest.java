package com.example.test.service;

import com.example.test.mapper.InvoiceDto;
import com.example.test.model.Invoice;
import com.example.test.model.User;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInvoice() {
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        when(invoiceRepository.findAll()).thenReturn(List.of(invoice1, invoice2));

        List<Invoice> result = invoiceService.getInvoice();

        Assertions.assertEquals(List.of(invoice1, invoice2), result);
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    void testGetInvoiceById() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        Optional<Invoice> result = invoiceService.getInvoice(invoiceId);

        Assertions.assertEquals(Optional.of(invoice), result);
        verify(invoiceRepository, times(1)).findById(invoiceId);
    }

    @Test
    void testCreateInvoice() {
        Long userId = 1L;
        User user = new User();
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);

        Invoice result = invoiceService.createInvoice(userId);

        Assertions.assertEquals(invoice, result);
        Assertions.assertEquals(user, invoice.getUser());
        verify(userRepository, times(1)).findById(userId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void testCreateInvoice_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Invoice result = invoiceService.createInvoice(userId);

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getUser());

        verify(userRepository, times(1)).findById(userId);
        verifyNoInteractions(invoiceRepository);
    }

    @Test
    void testUpdateInvoice() {
        Long invoiceId = 1L;
        InvoiceDto invoiceDto = new InvoiceDto();
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        Invoice result = invoiceService.updateInvoice(invoiceId, invoiceDto);

        Assertions.assertEquals(invoice, result);
        Assertions.assertEquals(invoiceDto.getStatus(), invoice.getStatus());
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void testDeleteInvoice() {
        Long invoiceId = 1L;
        doNothing().when(invoiceRepository).deleteById(invoiceId);

        boolean result = invoiceService.deleteInvoice(invoiceId);

        Assertions.assertTrue(result);
        verify(invoiceRepository, times(1)).deleteById(invoiceId);
    }

    @Test
    void testDeleteInvoice_NotFound() {
        Long invoiceId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(invoiceRepository).deleteById(invoiceId);

        boolean result = invoiceService.deleteInvoice(invoiceId);

        Assertions.assertFalse(result);
        verify(invoiceRepository, times(1)).deleteById(invoiceId);
    }
}
