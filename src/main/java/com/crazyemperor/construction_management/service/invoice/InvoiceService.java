package com.crazyemperor.construction_management.service.invoice;

import com.crazyemperor.construction_management.entity.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {

    List<Invoice> getUnpaid(long id) throws Exception;
    List<Invoice> selectedForPay(long id) throws Exception;
    BigDecimal paymentAmount(long id) throws Exception;
}
