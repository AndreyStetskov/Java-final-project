package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.invoice.InvoiceCRUDService;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return invoices != null ? ResponseEntity.ok(invoices) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceCRUDService.getInvoiceByID(id);

        return invoice != null ? ResponseEntity.ok(invoice) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Invoice> deleteByID(@PathVariable Long id, @RequestBody Invoice invoice) {
        Invoice deactivate = invoiceCRUDService.deleteInvoiceByID(id, invoice);

        return invoice != null ? ResponseEntity.ok(deactivate) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<Invoice> deleteByName(@PathVariable String name, @RequestBody Invoice invoice) {
        Invoice deactivated = invoiceCRUDService.deleteInvoiceByName(name, invoice);

        return invoice != null ? ResponseEntity.ok(deactivated) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/unpaid/{memberId}")
    public ResponseEntity<List<Invoice>> getUnpaidInvoices(@PathVariable Long memberId) {
        List<Invoice> unpaid = invoiceService.getUnpaid(memberId);

        return unpaid != null ? ResponseEntity.ok(unpaid) : ResponseEntity.noContent().build();
    }


}
