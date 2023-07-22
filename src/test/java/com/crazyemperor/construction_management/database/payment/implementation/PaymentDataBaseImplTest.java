package com.crazyemperor.construction_management.database.payment.implementation;

import com.crazyemperor.construction_management.entity.Payment;
import com.crazyemperor.construction_management.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class PaymentDataBaseImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentDataBaseImpl paymentDB;

    @Test
    void getPayments_ReturnListOfAllPaymentsOfThreeObjects_Success() {

//        given
        List<Payment> expected = new ArrayList<>();
        expected.add(new Payment());
        expected.add(new Payment());
        expected.add(new Payment());

        when(paymentRepository.findAll()).thenReturn(expected);

//        when
        List<Payment> actual = paymentDB.getPayments();

//        then
        assertEquals(expected, actual);
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void getByID_ReturnOfferById_Success() {

//        given
        Payment expected = new Payment();

        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(expected));

//        when
        Optional<Payment> actualOptional = paymentRepository.findById(anyLong());
        Payment actual = actualOptional.get();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getByName() {

//        given
        Payment expected = new Payment();

        when(paymentRepository.findByTitle(anyString())).thenReturn(expected);

//        when
        Payment actual = paymentRepository.findByTitle(anyString());

//        then
        assertEquals(expected, actual);
    }
}