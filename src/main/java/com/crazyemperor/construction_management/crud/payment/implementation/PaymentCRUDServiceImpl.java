package com.crazyemperor.construction_management.crud.payment.implementation;

import com.crazyemperor.construction_management.crud.payment.PaymentCRUDService;
import com.crazyemperor.construction_management.database.payment.PaymentDataBaseService;
import com.crazyemperor.construction_management.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentCRUDServiceImpl implements PaymentCRUDService {

    private final PaymentDataBaseService paymentDBService;


    @Override
    public List<Payment> getAllPayments() {
        return paymentDBService.getPayments();
    }

    @Override
    public Payment getPaymentByID(long id) {
        return paymentDBService.getByID(id);
    }

    @Override
    public Payment getPaymentByName(String name) {
        return paymentDBService.getByName(name);
    }
}
