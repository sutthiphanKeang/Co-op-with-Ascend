package com.example.test.service;

import com.example.test.exception.ExceptionResolver;
import com.example.test.mapper.InvoiceDto;
import com.example.test.model.Invoice;
import com.example.test.model.User;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInvoice() {
        List<Invoice> expectedInvoices = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setStatus(Boolean.FALSE);
        expectedInvoices.add(invoice);

        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        List<Invoice> actualInvoices = invoiceService.getInvoice();

        Assertions.assertEquals(expectedInvoices, actualInvoices);
        verify(invoiceRepository, times(1)).findAll();
    }

//    @Test
//    void testGetInvoiceById() {
//        Long invoiceId = 1L;
//        Invoice expectedInvoice = new Invoice();
//        expectedInvoice.setId(invoiceId);
//        expectedInvoice.setStatus(Boolean.FALSE);
//
//        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));
//
//        Invoice actualInvoice = invoiceService.getInvoice(invoiceId);
//
//        when(invoiceRepository.findById(invoiceId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> invoiceService.getInvoice(invoiceId));
//        Assertions.assertEquals(expectedInvoice, actualInvoice);
//        verify(invoiceRepository, times(2)).findById(invoiceId);
//    }

    @Test
    void testCreateInvoice() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setUser(user);
        expectedInvoice.setStatus(Boolean.FALSE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(expectedInvoice);

        Invoice actualInvoice = invoiceService.createInvoice(userId);

        Assertions.assertEquals(expectedInvoice, actualInvoice);
        Assertions.assertEquals(user, expectedInvoice.getUser());
        verify(userRepository, times(1)).findById(userId);
        verify(invoiceRepository, times(1)).save(expectedInvoice);
    }

    @Test
    void testUpdateInvoice() {
        Long invoiceId = 1L;
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setStatus(Boolean.TRUE);

        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setId(invoiceId);
        expectedInvoice.setStatus(invoiceDto.getStatus());

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(expectedInvoice);

        Invoice actualInvoice = invoiceService.updateInvoice(invoiceId, invoiceDto);

        when(invoiceRepository.findById(invoiceId)).thenThrow(ExceptionResolver.NotFoundException.class);

        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> invoiceService.updateInvoice(invoiceId, invoiceDto));
        Assertions.assertEquals(expectedInvoice, actualInvoice);
        Assertions.assertEquals(invoiceDto.getStatus(), expectedInvoice.getStatus());
        verify(invoiceRepository, times(2)).findById(invoiceId);
        verify(invoiceRepository, times(1)).save(expectedInvoice);
    }

    @Test
    void testDeleteInvoice() {
        Long invoiceId = 1L;
        User user = new User();
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setUser(user);
        expectedInvoice.setStatus(Boolean.TRUE);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        boolean resultTrue = invoiceService.deleteInvoice(invoiceId);

        when(invoiceRepository.findById(invoiceId)).thenThrow(ExceptionResolver.NotFoundException.class);

        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> invoiceService.deleteInvoice(invoiceId));
        Assertions.assertTrue(resultTrue);
    }
}
