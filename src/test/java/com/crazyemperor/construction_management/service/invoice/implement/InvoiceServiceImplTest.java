package com.crazyemperor.construction_management.service.invoice.implement;

import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.entity.auxillirary.InvoiceStatus;
import com.crazyemperor.construction_management.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class InvoiceServiceImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    void getUnpaid_ReturnAllUnpaidInvoices_Success() {

//        given
        List<Invoice> expected = new ArrayList<>();
        Invoice invoice = new Invoice();
        Invoice invoiceTwo = new Invoice();
        long id = anyLong();

        invoice.setDeleted(false);
        invoice.setPaidStatus(InvoiceStatus.ACTUAL);
        expected.add(invoice);

        invoiceTwo.setDeleted(false);
        invoiceTwo.setPaidStatus(InvoiceStatus.ACTUAL);
        expected.add(invoiceTwo);

        when(invoiceRepository.findAllUnpaid(id)).thenReturn(expected);

//        when
        List<Invoice> actual = invoiceService.getUnpaid(id);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void selectedForPay_ReturnSelectedUnpaidInvoices_Success() {

//        given
        List<Invoice> expected = new ArrayList<>();
        Invoice invoice = new Invoice();
        Invoice invoiceTwo = new Invoice();
        long id = anyLong();

        invoice.setDeleted(false);
        invoice.setPaidStatus(InvoiceStatus.ACTUAL);
        expected.add(invoice);

        invoiceTwo.setDeleted(false);
        invoiceTwo.setPaidStatus(InvoiceStatus.ACTUAL);
        expected.add(invoiceTwo);

        when(invoiceRepository.findAllUnpaid(id)).thenReturn(expected);

//        when
        List<Invoice> actual = invoiceService.getUnpaid(id);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void paymentAmount_ReturnAmountFromAllSelectedInvoices_Success() {

//        given
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice = new Invoice();
        Invoice invoiceTwo = new Invoice();
        long id = anyLong();

        invoice.setDeleted(false);
        invoice.setPaidStatus(InvoiceStatus.ACTUAL);
        invoice.setAmount(new BigDecimal("115638887"));
        invoiceList.add(invoice);

        invoiceTwo.setDeleted(false);
        invoiceTwo.setPaidStatus(InvoiceStatus.ACTUAL);
        invoice.setAmount(new BigDecimal("45234"));
        invoiceList.add(invoiceTwo);

        BigDecimal expected = new BigDecimal("115684121");

        when(invoiceRepository.findAllUnpaid(id)).thenReturn(invoiceList);

//        when
        BigDecimal actual = invoiceService.paymentAmount(id);

//        then
        assertEquals(expected, actual);
    }
}