package com.crazyemperor.construction_management.database.invoice.implementation;

import com.crazyemperor.construction_management.database.invoice.InvoiceDataBaseService;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.repository.InvoiceRepository;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    @Cacheable("Invoices")
    public List<Invoice> getInvoices() {
        Optional<List<Invoice>> invoices = Optional.of(invoiceRepository.findAll());

        return invoices
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @Cacheable("Invoices")
    public Invoice getByID(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @CacheEvict("Invoices")
    public void deleteByName(String name) {
        Optional<Invoice> invoiceOptional = Optional.ofNullable(invoiceRepository.findByTitle(name));
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
        }
        else throw new DataNotFoundException();
    }

    @SneakyThrows
    @Override
    @CacheEvict("Invoices")
    public void deleteByID(long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
    }
}
