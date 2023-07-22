package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.entity.auxillirary.InvoiceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    void findByTitle_ReturnInvoiceByName_Success() {

//        given
        Invoice expected = new Invoice();
        Invoice invoice = new Invoice();

        expected.setTitle("Installation work");
        expected.setAmount(new BigDecimal("60000"));
        expected.setPaidStatus(InvoiceStatus.ACTUAL);
        expected.setDeadline(LocalDate.of(2023, 12, 2));


        invoice.setTitle("Constructor on \"Skyscraper WC\"");
        invoice.setAmount(new BigDecimal("4588000"));
        invoice.setPaidStatus(InvoiceStatus.PAID);
        invoice.setDeadline(LocalDate.of(2025, 5, 2));

        invoiceRepository.save(expected);
        invoiceRepository.save(invoice);

//        when
        Invoice actual = invoiceRepository.findByTitle("Constructor on \"Skyscraper WC\"");

//        then
        assertEquals(expected, actual);
    }

    @Test
    void findAllUnpaid_ReturnAllUnpaidInvoices_Success() {

//        given
        Long id = 1L;
        Invoice invoice = new Invoice();
        Invoice invoiceTwo = new Invoice();
        Member member = new Member();

        member.setId(id);

        invoice.setTitle("Installation work");
        invoice.setAmount(new BigDecimal("60000"));
        invoice.setPaidStatus(InvoiceStatus.ACTUAL);
        invoice.setDeadline(LocalDate.of(2023, 12, 2));

        invoiceTwo.setTitle("Constructor on \"Skyscraper WC\"");
        invoiceTwo.setAmount(new BigDecimal("4588000"));
        invoiceTwo.setPaidStatus(InvoiceStatus.PAID);
        invoiceTwo.setDeadline(LocalDate.of(2025, 5, 2));

        invoiceRepository.save(invoice);
        invoiceRepository.save(invoiceTwo);

//        when
        List<Invoice> actual = invoiceRepository.findAllUnpaid(member.getId());

//        then
        assertEquals(2, actual.size());
    }
}