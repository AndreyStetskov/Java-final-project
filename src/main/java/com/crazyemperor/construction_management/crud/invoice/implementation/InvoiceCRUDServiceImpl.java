package com.crazyemperor.construction_management.crud.invoice.implementation;

import com.crazyemperor.construction_management.database.invoice.InvoiceDataBaseService;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.crud.invoice.InvoiceCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceCRUDServiceImpl implements InvoiceCRUDService {

    private final InvoiceDataBaseService invoiceDB;


    @Override
    public Invoice add(Invoice constructionSite) {
        return invoiceDB.addInvoice(constructionSite);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDB.getInvoices();
    }

    @Override
    public Invoice getInvoiceByID(long id) {
        return invoiceDB.getByID(id);
    }

    @Override
    public void deleteInvoiceByName(String name) {
        invoiceDB.deleteByName(name);
    }

    @Override
    public void deleteInvoiceByID(long id) {
        invoiceDB.deleteByID(id);
    }
}
