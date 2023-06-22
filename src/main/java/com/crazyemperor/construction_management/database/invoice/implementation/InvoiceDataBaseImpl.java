package com.crazyemperor.construction_management.database.invoice.implementation;

import com.crazyemperor.construction_management.database.invoice.InvoiceDataBaseService;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceDataBaseImpl implements InvoiceDataBaseService {

    private final InvoiceRepository invoiceRepository;


    @Override
    @CacheEvict("Invoices")
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    @Cacheable("Invoices")
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    @Cacheable("Invoices")
    public Invoice getByID(long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    @CacheEvict("Invoices")
    public Invoice deleteByName(String name, Invoice delete) {
        Optional<Invoice> invoiceOptional = Optional.ofNullable(invoiceRepository.findByTitle(name));
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
        }
        return invoiceOptional.orElse(null);
    }

    @Override
    @CacheEvict("Invoices")
    public Invoice deleteByID(long id, Invoice delete) {
        Optional<Invoice> invoiceOptional = Optional.ofNullable(invoiceRepository.findById(id));
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
        }
        return invoiceOptional.orElse(null);
    }
}
