package com.crazyemperor.construction_management.database.invoice;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Invoice;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> invoice.
 * <p><img src = "https://bs-uploads.toptal.io/blackfish-uploads/components/seo/content/og_image_file/og_image/1282569/0712-Bad_Practices_in_Database_Design_-_Are_You_Making_These_Mistakes_Dan_Social-754bc73011e057dc76e55a44a954e0c3.png" width = "500" height = "auto" alt = "database"></p>
 * @author Stetskov
 * @version 1.0
 */
public interface InvoiceDataBaseService {

    /**
     * Adding some invoice
     * @param invoice a bill for paid
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
     * @param id ID of an invoices to be found
     * @return An invoice by ID
     * @throws NoDataFoundException
     *          thrown if no invoice was found.
     */
    Invoice getByID(long id);
    /**
     * Deleting an invoice by name
     * @param name name of an invoice to be deleted
     * @throws NoDataFoundException
     *          thrown if there's no such invoice name.
     */
    void deleteByName(String name);
    /**
     * Deleting an invoice by name
     * @param id ID of a invoice to be deleted
     * @throws NoDataFoundException
     *          thrown if there's no such invoice ID.
     */
    void deleteByID(long id);
}
