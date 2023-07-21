package com.crazyemperor.construction_management.service.invoice;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Invoice;

import java.math.BigDecimal;
import java.util.List;

/**
 * <b>Retrieving</b> all unpaid invoices,
 *  <b>selected<b/> invoices for paid
 *  and <b>calculation of a total amount</b> invoices which must be paid,
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface InvoiceService {

    /**
     * Retrieving all unpaid invoices
     * @param id - ID of members who finding unpaid invoices
     * @return List of existing unpaid invoices
     * @throws NoDataFoundException
     *          thrown if no unpaid invoices were found.
     */
    List<Invoice> getUnpaid(long id);
    /**
     * Select unpaid invoices for paid
     * @param id - ID of members who selecting found unpaid invoices
     * @throws NoDataFoundException
     *          thrown if no unpaid invoices were found.
     */
    List<Invoice> selectedForPay(long id);
    /**
     * Calculation of a total amount invoices which must be paid
     * @param id - ID of members who gonna pay invoices
     * @return List of existing unpaid invoices
     * @throws NoDataFoundException
     *          thrown if no unpaid invoices were found.
     */
    BigDecimal paymentAmount(long id);
}
