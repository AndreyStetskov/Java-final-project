package com.crazyemperor.construction_management.database.payment.implementation;

import com.crazyemperor.construction_management.database.payment.PaymentDataBaseService;
import com.crazyemperor.construction_management.entity.Payment;
import com.crazyemperor.construction_management.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentDataBaseImpl implements PaymentDataBaseService{

    private final  PaymentRepository paymentRepository;


    @Override
    @CacheEvict("Payments")
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    @Cacheable("Payments")
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    @Cacheable("Payments")
    public Payment getByID(long id) {
        return paymentRepository.findById(id);
    }

    @Override
    @Cacheable("Payments")
    public Payment getByName(String name) {
        return paymentRepository.findByTitle(name);
    }
}
