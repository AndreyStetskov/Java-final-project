package com.crazyemperor.construction_management.crud.invoice;

import com.crazyemperor.construction_management.entity.Invoice;

import java.util.List;

public interface InvoiceCRUDService {

    Invoice add(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice getInvoiceByID(long id);
    void deleteInvoiceByName(String name);
    void deleteInvoiceByID(long id);
}
