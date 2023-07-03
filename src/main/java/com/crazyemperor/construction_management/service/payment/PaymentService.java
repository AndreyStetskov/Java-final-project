package com.crazyemperor.construction_management.service.payment;

import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.entity.Payment;

/**
 * <b>Creating</b> payment.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface PaymentService {

    /**
     * Adding new payment
     * @param payment - Payment by invoices
     */
    Payment addPayment(Payment payment, BankResponse response);
}
