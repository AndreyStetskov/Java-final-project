package com.crazyemperor.construction_management.database.payment.implementation;

import com.crazyemperor.construction_management.database.payment.PaymentDataBaseService;
import com.crazyemperor.construction_management.entity.Payment;
import com.crazyemperor.construction_management.repository.PaymentRepository;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    @Cacheable("payments")
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @SneakyThrows
    @Override
    @Cacheable("payments")
    public Payment getByID(long id) {
        return paymentRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @Override
    @Cacheable("payments")
    public Payment getByName(String name) {
        return paymentRepository.findByTitle(name);
    }
}
