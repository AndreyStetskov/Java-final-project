package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.crud.payment.PaymentCRUDService;
import com.crazyemperor.construction_management.entity.Payment;
import com.crazyemperor.construction_management.service.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class PaymentControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private PaymentCRUDService paymentCRUDService;
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;


    @Test
    void createPayment_AddNewPayment_Success() {

//        given
        Payment payment = new Payment();
        BankResponse response = new BankResponse(any(), true);
        ResponseEntity<Payment> expected = new ResponseEntity<>(payment, HttpStatus.CREATED);

        when(paymentService.addPayment(any(), response)).thenReturn(payment);

//        when
        ResponseEntity<Payment> actual = paymentController.createPayment(payment, response);

//        then
        assertEquals(expected, actual);
        verify(paymentService, times(1)).addPayment(payment, response);
    }

    @Test
    void createPayment_ErrorAddingPayment_Unsuccessful() {

//        given
        Payment payment = new Payment();
        BankResponse response = new BankResponse(any(), false);
        ResponseEntity<Payment> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        when(paymentService.addPayment(any(), response)).thenReturn(null);

//        when
        ResponseEntity<Payment> actual = paymentController.createPayment(payment, response);

//        then
        assertEquals(expected, actual);
        verify(paymentService, times(1)).addPayment(payment, response);
    }

    @Test
    void allPayments_ReturnListOfAllPaymentsOfThreeObjects_Success() {

//        given
        List<Payment> paymentList = new ArrayList<>();

        paymentList.add(new Payment());
        paymentList.add(new Payment());
        paymentList.add(new Payment());

        ResponseEntity<List<Payment>> expected = new ResponseEntity<>(paymentList, HttpStatus.OK);

        when(paymentCRUDService.getAllPayments()).thenReturn(paymentList);

//        when
        ResponseEntity<List<Payment>> actual = paymentController.allPayments();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getPaymentById_ReturnPaymentById_Success() {

//        given
        Long idPayment = anyLong();
        Payment payment = new Payment();
        payment.setId(idPayment);

        ResponseEntity<Payment> expected = new ResponseEntity<>(payment, HttpStatus.OK);

        when(paymentCRUDService.getPaymentByID(idPayment)).thenReturn(payment);

//        when
        ResponseEntity<Payment> actual = paymentController.getPaymentById(idPayment);

//        then
        assertEquals(expected, actual);
    }
}