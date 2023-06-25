package com.crazyemperor.construction_management.database.invoice;

import com.crazyemperor.construction_management.entity.Invoice;

import java.util.List;

public interface InvoiceDataBaseService {

    Invoice addInvoice(Invoice invoice);
    List<Invoice> getInvoices();
    Invoice getByID(long id);
    void deleteByName(String name);
    void deleteByID(long id);
}
