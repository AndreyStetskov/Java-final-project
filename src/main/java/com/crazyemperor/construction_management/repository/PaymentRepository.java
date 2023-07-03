package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByTitle(final String name);
//    @Query("INSERT INTO Payments(payment_ID, title, created_at, invoice_id, acceptor_id, offerer_id, description) " +
//            "VALUE ")
//    Payment create();
}
