package com.crazyemperor.construction_management.database.invoice;

import com.crazyemperor.construction_management.entity.Invoice;

import java.util.List;

public interface InvoiceDataBaseService {

    Invoice addInvoice(Invoice invoice);
    List<Invoice> getInvoices();
    Invoice getByID(long id);
    Invoice deleteByName(String name, Invoice delete);
    Invoice deleteByID(long id, Invoice delete);
}
