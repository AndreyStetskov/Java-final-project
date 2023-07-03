package com.crazyemperor.construction_management.crud.payment;

import com.crazyemperor.construction_management.entity.Payment;

import java.util.List;
public interface PaymentCRUDService {

    List<Payment> getAllPayments();
    Payment getPaymentByID(long id);
    Payment getPaymentByName(String name);

}
