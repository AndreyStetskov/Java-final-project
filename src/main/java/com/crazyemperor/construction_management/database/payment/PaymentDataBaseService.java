package com.crazyemperor.construction_management.database.payment;

import com.crazyemperor.construction_management.entity.Payment;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> payment.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface PaymentDataBaseService {

    /**
     * Retrieving all payments
     * @return List of existing payments
     * @throws DataNotFoundException
     *          thrown if no payments were found.
     */
    List<Payment> getPayments();
    /**
     * Retrieving a payment by ID
     * @param id - ID of a payment to be found
     * @return A payment by ID
     * @throws DataNotFoundException
     *          thrown if no payment was found.
     */
    Payment getByID(long id);
    /**
     * Retrieving a payment by ID
     * @param name - name of a payment to be found
     * @return A payment by name
     * @throws DataNotFoundException
     *          thrown if no payment was found.
     */
    Payment getByName(String name);
}
