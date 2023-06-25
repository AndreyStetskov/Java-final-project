package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.invoice.InvoiceCRUDService;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceCRUDService invoiceCRUDService;
    private final InvoiceService invoiceService;


    @PostMapping(value = "/create_new_invoice")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        invoiceCRUDService.add(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Invoice>> allInvoices() {
        List<Invoice> invoices = invoiceCRUDService.getAllInvoices();

        if (invoices != null && !invoices.isEmpty()) {
            return ResponseEntity.ok(invoices);
        } else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceCRUDService.getInvoiceByID(id);

        return invoice != null ? ResponseEntity.ok(invoice) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable Long id) {
        invoiceCRUDService.deleteInvoiceByID(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<Long> deleteByName(@PathVariable String name) {
        invoiceCRUDService.deleteInvoiceByName(name);

        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping(value = "/{memberId}/unpaid")
    public ResponseEntity<List<Invoice>> getUnpaidInvoices(@PathVariable Long memberId) {
        List<Invoice> unpaid = invoiceService.getUnpaid(memberId);

        if (unpaid != null && !unpaid.isEmpty()) {
            return ResponseEntity.ok(unpaid);
        } else return ResponseEntity.noContent().build();

    }

    @SneakyThrows
    @PutMapping(value = "/{memberId}/unpaid/select")
    public ResponseEntity<List<Invoice>> selected(@PathVariable Long memberId) {
        List<Invoice> selected = invoiceService.selectedForPay(memberId);

        if (selected != null && !selected.isEmpty()) {
            return ResponseEntity.ok(selected);
        } else return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @GetMapping(value = "/{memberId}/unpaid/sum")
    public BigDecimal getSum(@PathVariable Long memberId) {
        return invoiceService.paymentAmount(memberId);
    }

}
