package com.crazyemperor.construction_management.database.invoice.implementation;

import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


class InvoiceDataBaseImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceDataBaseImpl invoiceDB;


    @Test
    void addInvoice_AddNewInvoice_Success() {

//        given
        Invoice expected = new Invoice();

//        when
        invoiceDB.addInvoice(expected);

//        then
        verify(invoiceRepository, times(1)).save(expected);
    }

    @Test
    void getInvoices_ReturnListOfAllInvoicesOfThreeObjects_Success() {

//        given
        List<Invoice> expected = new ArrayList<>();
        expected.add(new Invoice());
        expected.add(new Invoice());
        expected.add(new Invoice());

        when(invoiceRepository.findAll()).thenReturn(expected);

//        when
        List<Invoice> actual = invoiceDB.getInvoices();

//        then
        assertEquals(expected, actual);
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    void getByID_ReturnInvoiceById_Success() {

//        given
        Invoice expected = new Invoice();

        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(expected));

//        when
        Optional<Invoice> actualOptional = invoiceRepository.findById(anyLong());
        Invoice actual = actualOptional.get();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        String name = anyString();
        Invoice actual = new Invoice();
        actual.setTitle(name);
        actual.setDeleted(false);
        Invoice expected = new Invoice();
        expected.setTitle(name);
        expected.setDeleted(true);

        when(invoiceRepository.findByTitle(name)).thenReturn(actual);

//        when
        invoiceDB.deleteByName(actual.getTitle());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        Long id = anyLong();

        Optional<Invoice> optional = Optional.ofNullable(new Invoice());
        Invoice actual = optional.get();
        actual.setId(id);
        actual.setDeleted(false);

        Invoice expected = new Invoice();
        expected.setId(id);
        expected.setDeleted(true);

        when(invoiceRepository.findById(id)).thenReturn(optional);

//        when
        invoiceDB.deleteByID(actual.getId());

//        then
        assertEquals(expected, actual);
    }
}