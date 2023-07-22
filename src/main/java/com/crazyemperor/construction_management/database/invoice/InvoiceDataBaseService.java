package com.crazyemperor.construction_management.database.invoice;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Invoice;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> invoice.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface InvoiceDataBaseService {

    /**
     * Adding some invoice
     * @param invoice - A bill for paid
     */
    Invoice addInvoice(Invoice invoice);
    /**
     * Retrieving all invoices
     * @return List of existing invoices
     * @throws NoDataFoundException
     *          thrown if no invoices were found.
     */
    List<Invoice> getInvoices();
    /**
     * Retrieving an invoice by ID
     * @param id - ID of an invoices to be found
     * @return An invoice by ID
     * @throws NoDataFoundException
     *          thrown if no invoice was found.
     */
    Invoice getByID(long id);
    /**
     * Deleting an invoice by name
     * @param name - name of an invoice to be deleted
     * @throws NoDataFoundException
     *          thrown if There's no such invoice name.
     */
    void deleteByName(String name);
    /**
     * Deleting an invoice by name
     * @param id - ID of a invoice to be deleted
     * @throws NoDataFoundException
     *          thrown if There's no such invoice ID.
     */
    void deleteByID(long id);
}
