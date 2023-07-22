package com.crazyemperor.construction_management.service.payment;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.entity.Payment;

import java.util.List;

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
    /**
     * Retrieving all unpaid invoices
     * @return List of a name of a members who paid anything and a construction site from which a payment was made
     * @throws NoDataFoundException
     *          thrown if no one members with e-mail was found.
     */
    List<Payment> geAllPaidOrganisations();
}
