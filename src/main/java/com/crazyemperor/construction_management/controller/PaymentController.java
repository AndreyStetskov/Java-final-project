package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.bank.model.BankResponse;
import com.crazyemperor.construction_management.crud.payment.PaymentCRUDService;
import com.crazyemperor.construction_management.entity.Payment;
import com.crazyemperor.construction_management.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentCRUDService paymentCRUDService;
    private final PaymentService paymentService;


    @PostMapping(value = "/create-new-payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment, @RequestBody BankResponse response) {
        paymentService.addPayment(payment, response);

        return response.success() ? ResponseEntity.status(HttpStatus.CREATED).body(payment) : ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Payment>> allPayments() {
        List<Payment> payments = paymentCRUDService.getAllPayments();

        if (payments != null && !payments.isEmpty()) {
            return ResponseEntity.ok(payments);
        }
        else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentCRUDService.getPaymentByID(id);

        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{name}")
    public ResponseEntity<Payment> getPaymentByName(@PathVariable String name) {
        Payment payment = paymentCRUDService.getPaymentByName(name);

        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.noContent().build();
    }
}
