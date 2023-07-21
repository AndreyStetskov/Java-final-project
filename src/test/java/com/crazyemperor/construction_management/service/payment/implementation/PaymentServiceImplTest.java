package com.crazyemperor.construction_management.service.payment.implementation;

import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.entity.*;
import com.crazyemperor.construction_management.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void addPayment() {

//        given
        Payment expected = new Payment();
        BankResponse response = new BankResponse(LocalDateTime.now(), true);

//        when
        paymentService.addPayment(expected, response);

//        then
        verify(paymentRepository, times(1)).save(expected);
    }

    @Test
    void geAllPaidOrganisations_ReturnAllMembersWhoPaid_Success() {

//        given
        List<Payment> expected = new ArrayList<>();
        Payment payment = new Payment();
        Payment paymentTwo = new Payment();

        payment.setPaid(new Invoice());
        payment.getPaid().setPayer(new Member());
        payment.getPaid().getPayer().setOrganisation(new Organisation());
        payment.getPaid().getPayer().getOrganisation().setName("Some Pupkin");
        payment.getPaid().setInvoice(new ConstructionSite());
        payment.getPaid().getInvoice().setTitle("Birdhouse");

        paymentTwo.setPaid(new Invoice());
        paymentTwo.getPaid().setPayer(new Member());
        paymentTwo.getPaid().getPayer().setOrganisation(new Organisation());
        paymentTwo.getPaid().getPayer().getOrganisation().setName("Serious Organisation");
        paymentTwo.getPaid().setInvoice(new ConstructionSite());
        paymentTwo.getPaid().getInvoice().setTitle("Serious House");

        expected.add(payment);
        expected.add(paymentTwo);

        when(paymentRepository.findAllPaidMembers()).thenReturn(expected);

//        when
        List<Payment> actual = paymentService.geAllPaidOrganisations();

//        then
        assertEquals(expected, actual);
    }
}