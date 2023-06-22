package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.payment.PaymentCRUDService;
import com.crazyemperor.construction_management.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentCRUDService paymentCRUDService;


    @PostMapping(value = "/create_new_payment")
    public ResponseEntity<Payment> createOffer(@RequestBody Payment payment) {
        paymentCRUDService.add(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Payment>> allPayments() {
        List<Payment> payments = paymentCRUDService.getAllPayments();

        return payments != null ? ResponseEntity.ok(payments) : ResponseEntity.noContent().build();
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
