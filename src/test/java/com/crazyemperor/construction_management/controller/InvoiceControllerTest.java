package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.invoice.InvoiceCRUDService;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.service.invoice.InvoiceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class InvoiceControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private InvoiceCRUDService invoiceCRUDService;
    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    private final Invoice invoice = new Invoice();
    private final List<Invoice> invoiceList = new ArrayList<>();


    @Test
    void createInvoice_AddNewInvoice_Success() {

//        given
        ResponseEntity<Invoice> expected = new ResponseEntity<>(invoice, HttpStatus.CREATED);

        when(invoiceCRUDService.add(any())).thenReturn(expected.getBody());

//        when
        ResponseEntity<Invoice> actual = invoiceController.createInvoice(invoice);

//        then
        assertEquals(expected, actual);
        verify(invoiceCRUDService, times(1)).add(invoice);
    }

    @Test
    void allInvoices_ReturnListOfAllInvoicesOfThreeObjects_Success() {

//        given
        invoiceList.add(new Invoice());
        invoiceList.add(new Invoice());
        invoiceList.add(new Invoice());

        ResponseEntity<List<Invoice>> expected = new ResponseEntity<>(invoiceList, HttpStatus.OK);

        when(invoiceCRUDService.getAllInvoices()).thenReturn(invoiceList);

//        when
        ResponseEntity<List<Invoice>> actual = invoiceController.allInvoices();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getInvoiceById_ReturnInvoiceById_Success() {

//        given
        Long idInvoice = anyLong();
        invoice.setId(idInvoice);

        ResponseEntity<Invoice> expected = new ResponseEntity<>(invoice, HttpStatus.OK);

        when(invoiceCRUDService.getInvoiceByID(idInvoice)).thenReturn(invoice);

//        when
        ResponseEntity<Invoice> actual = invoiceController.getInvoiceById(idInvoice);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        invoice.setDeleted(true);

        ResponseEntity<Invoice> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<Long> actual = invoiceController.deleteByID(anyLong());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        invoice.setDeleted(true);

        ResponseEntity<String> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<String> actual = invoiceController.deleteByName(anyString());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getUnpaidInvoices_ReturnListOfAllInvoicesOfThreeObjects_Success() {

//        given
        long idMember = anyLong();

        invoiceList.add(new Invoice());
        invoiceList.add(new Invoice());
        invoiceList.add(new Invoice());

        ResponseEntity<List<Invoice>> expected = new ResponseEntity<>(invoiceList, HttpStatus.OK);

        when(invoiceService.getUnpaid(idMember)).thenReturn(invoiceList);

//        when
        ResponseEntity<List<Invoice>> actual = invoiceController.getUnpaidInvoices(idMember);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void selected() {

//        given
        long idMember = anyLong();

        invoiceList.add(new Invoice());
        invoiceList.add(new Invoice());
        invoiceList.add(new Invoice());

        ResponseEntity<List<Invoice>> expected = new ResponseEntity<>(invoiceList, HttpStatus.OK);

        when(invoiceService.selectedForPay(idMember)).thenReturn(invoiceList);

//        when
        ResponseEntity<List<Invoice>> actual = invoiceController.selected(idMember);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getSum() {

//        given
        long idMember = anyLong();
        BigDecimal amount = new BigDecimal("3211488");
        ResponseEntity<BigDecimal> expected = new ResponseEntity<>(HttpStatus.OK);

        when(invoiceService.paymentAmount(idMember)).thenReturn(amount);

//        when
        ResponseEntity<BigDecimal> actual = invoiceController.getSum(idMember);

//        then
        assertEquals(expected, actual);
    }
}