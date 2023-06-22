package com.crazyemperor.construction_management.database.payment;

import com.crazyemperor.construction_management.entity.Payment;

import java.util.List;

public interface PaymentDataBaseService {

    Payment addPayment(Payment offer);
    List<Payment> getPayments();
    Payment getByID(long id);
    Payment getByName(String name);
}
