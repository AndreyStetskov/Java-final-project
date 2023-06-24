package com.crazyemperor.construction_management.service.invoice;

import com.crazyemperor.construction_management.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getUnpaid(long id);
    List<Invoice> selectedForPay(List<Invoice> choice);
}
