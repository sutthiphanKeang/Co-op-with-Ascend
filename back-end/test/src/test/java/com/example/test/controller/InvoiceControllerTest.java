package com.example.test.controller;

import com.example.test.mapper.InvoiceDto;
import com.example.test.model.Invoice;
import com.example.test.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class InvoiceControllerTest {

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void testGetAllInvoice() throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setStatus(Boolean.FALSE);
        invoices.add(invoice);

        when(invoiceService.getInvoice()).thenReturn(invoices);

        mockMvc.perform(get("/api/get-invoice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(invoice.getId()))
                .andExpect(jsonPath("$[0].status").value(invoice.getStatus()));

        invoices.clear();
        mockMvc.perform(get("/api/get-invoice"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetInvoiceById() throws Exception {
        long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setStatus(Boolean.FALSE);

        when(invoiceService.getInvoice(invoiceId)).thenReturn(Optional.of(invoice));

        mockMvc.perform(get("/api/get-invoice/{id}", invoiceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(invoice.getId()))
                .andExpect(jsonPath("$.status").value(invoice.getStatus()));

        when(invoiceService.getInvoice(invoiceId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/get-invoice/{id}", invoiceId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateInvoice() throws Exception {
        long userId = 1L;
        Invoice invoice = new Invoice();

        when(invoiceService.createInvoice(userId)).thenReturn(invoice);

        mockMvc.perform(post("/api/post-invoice/{id}", userId))
                .andExpect(status().isCreated());

        when(invoiceService.createInvoice(userId)).thenReturn(null);

        mockMvc.perform(post("/api/post-invoice/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateInvoice() throws Exception {
        long invoiceId = 1L;
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setStatus(Boolean.TRUE);

        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setStatus(invoiceDto.getStatus());

        when(invoiceService.updateInvoice(eq(invoiceId), any(InvoiceDto.class))).thenReturn(invoice);

        mockMvc.perform(put("/api/put-invoice/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invoiceDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(invoice.getId()));

        when(invoiceService.updateInvoice(eq(invoiceId), any(InvoiceDto.class))).thenReturn(null);

        mockMvc.perform(put("/api/put-invoice/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invoiceDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteInvoice() throws Exception {
        long invoiceId = 1L;

        when(invoiceService.deleteInvoice(invoiceId)).thenReturn(true);

        mockMvc.perform(delete("/api/delete-invoice/{id}", invoiceId))
                .andExpect(status().isGone());

        when(invoiceService.deleteInvoice(invoiceId)).thenReturn(false);

        mockMvc.perform(delete("/api/delete-invoice/{id}", invoiceId))
                .andExpect(status().isNotFound());
    }
}
