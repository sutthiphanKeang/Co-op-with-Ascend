package com.example.test.resolver;

import com.example.test.exception.GlobalExceptionHandler;
import com.example.test.mapper.InvoiceDto;
import com.example.test.mapper.ProductDto;
import com.example.test.model.Invoice;
import com.example.test.model.Product;
import com.example.test.service.InvoiceService;
import com.example.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@SchemaMapping(typeName = "Invoice")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class InvoiceResolver {

    @Autowired
    private InvoiceService invoiceService;

    @QueryMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getInvoice();
    }

    @QueryMapping
    public Invoice getInvoiceById(@Argument("id") Long id) {
        return invoiceService.getInvoice(id);
    }

    @QueryMapping
    public List<Invoice> getInvoiceByUserId(@Argument("id") long id) {
        return invoiceService.getInvoice(id);
    }

    @QueryMapping
    public List<Invoice> getInvoiceByUserEmail(@Argument("email") String email) {
        return invoiceService.getInvoiceEmail(email);
    }

    @MutationMapping
    public Invoice createInvoice(@Argument("userId") long userId) {
        return invoiceService.createInvoice(userId);
    }

    @MutationMapping
    public Invoice updateInvoice(@Valid @Argument("id") long id, @Argument InvoiceDto invoiceDto) {
        return invoiceService.updateInvoice(id, invoiceDto);
    }

    @MutationMapping
    public Boolean deleteInvoice(@Argument("id") long id) {
        return invoiceService.deleteInvoice(id);
    }
}
