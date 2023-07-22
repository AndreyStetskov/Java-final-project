package com.crazyemperor.construction_management.database.invoice.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
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
    @CacheEvict("invoices")
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    @Cacheable("invoices")
    public List<Invoice> getInvoices() {
        Optional<List<Invoice>> invoices = Optional.of(invoiceRepository.findAll());

        return invoices
                .orElseThrow(() -> {
                    new NoDataFoundException("No one invoice found");
                    return null;
                });
    }

    @Override
    @Cacheable("invoices")
    public Invoice getByID(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No invoice found for id %d", id));
                    return null;
                });
    }

    @Override
    @CacheEvict("invoices")
    public void deleteByName(String name) {
        Optional<Invoice> invoiceOptional = Optional.ofNullable(invoiceRepository.findByTitle(name));
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
        }
        else try {
            throw new NoDataFoundException("This invoice didn't find");
        } catch (NoDataFoundException e) {
            throw new IllegalArgumentException("NoDataFoundException didn't work", e);
        }
    }


    @Override
    @CacheEvict("invoices")
    public void deleteByID(long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    try {
                        throw new NoDataFoundException(String.format("No invoice found for oid %d", id));
                    } catch (NoDataFoundException e) {
                        throw new IllegalArgumentException("NoDataFoundException didn't work", e);
                    }
                });
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
    }
}
