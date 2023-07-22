package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.*;
import com.crazyemperor.construction_management.entity.auxillirary.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void findByTitle() {
        Payment expected = new Payment();
        Payment payment = new Payment();

        expected.setTitle("Electrical work to Pupkin & Ko");
        expected.setDescription("Some description");

        payment.setTitle("Some payment");
        payment.setDescription("Urgently");
        paymentRepository.save(expected);
        paymentRepository.save(payment);

//        when
        Payment actual = paymentRepository.findByTitle("Electrical work to Pupkin & Ko");

//        then
        assertEquals(expected, actual);
    }

    @Test
    void findAllPaidMembers() {
        //        given
        Payment payment = new Payment();
        Invoice invoice = new Invoice();
        ConstructionSite constructionSite = new ConstructionSite();
        Member member = new Member();
        Organisation organisation = new Organisation();
        Set<MemberType> type = new HashSet<>();
        int expected = 1;

        organisation.setEin("46-4545464");
        organisation.setName("Pupkin and Ko");
        organisation.setDepartment(Department.ENERGY);
        organisation.setRegistration(LocalDate.of(1895,1,30));
        organisation.setLocation("Address");
        organisation.setStatus(OrganisationStatus.ACTIVE);
        member.setOrganisation(organisation);
        member.getOrganisation().getName();
        member.getOrganisation().setEmail("java@gmail.com");
        member.setType(type);
        member.getType().add(MemberType.CONSTRUCTOR);
        member.setStatus(MemberStatus.ACTIVE);
        constructionSite.setTitle("Skyscraper WC");
        constructionSite.setAmount(new BigDecimal("10230000"));
        constructionSite.setStatus(ConstructionSiteStatus.ACTIVE);
        invoice.setTitle("Installation work");
        invoice.setAmount(new BigDecimal("60000"));
        invoice.setPaidStatus(InvoiceStatus.ACTUAL);
        invoice.setDeadline(LocalDate.of(2023, 12, 2));
        payment.setPaid(invoice);
        payment.setTitle("Electrical work to Pupkin & Ko");

        paymentRepository.save(payment);

//        when
        List<Payment> actual = paymentRepository.findAllPaidMembers();

//        then
        assertEquals(expected, actual.size());
    }
}