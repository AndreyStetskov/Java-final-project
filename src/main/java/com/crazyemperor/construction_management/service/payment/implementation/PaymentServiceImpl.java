package com.crazyemperor.construction_management.service.payment.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.entity.Payment;
import com.crazyemperor.construction_management.repository.PaymentRepository;
import com.crazyemperor.construction_management.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @SneakyThrows
    @Override
    public Payment addPayment(Payment payment, BankResponse response) {
        if (!response.success()) {
            throw new IllegalAccessException("Payment hasn't been made");
        }
        return paymentRepository.save(payment);
    }

    @SneakyThrows
    @Override
    @Transactional
    public List<Payment> geAllPaidOrganisations() {
        Optional<List<Payment>> members = Optional.ofNullable(paymentRepository.findAllPaidMembers());

        return members
                .orElseThrow(() -> {
                    new NoDataFoundException("No one paid organisation found");
                    return null;
                });
    }
}
