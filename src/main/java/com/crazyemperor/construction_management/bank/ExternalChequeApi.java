package com.crazyemperor.construction_management.bank;

import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.entity.Member;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "cheque")
public interface ExternalChequeApi {

    @GetMapping
    @CircuitBreaker(name = "bank-breaker")
    @Retry(name = "bank-retry")
    BankResponse getCheque(
            @RequestParam(name = "payee") Member payee,
            @RequestParam(name = "payer") Member payer,
            @RequestParam(name = "amount") BigDecimal amount
            );
}
