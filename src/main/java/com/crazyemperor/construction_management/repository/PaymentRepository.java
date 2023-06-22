package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findById(final long id);
    Payment findByTitle(final String name);


}
