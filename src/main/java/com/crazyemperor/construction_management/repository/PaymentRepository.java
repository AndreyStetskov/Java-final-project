package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByTitle(final String name);

    @Query("SELECT org.name, building.title " +
            "FROM Payment pay " +
            "LEFT JOIN Invoice bill ON pay.id = bill.id " +
            "LEFT JOIN ConstructionSite building ON bill.id = building.id " +
            "LEFT JOIN Member member ON building.id = member.id " +
            "LEFT JOIN Organisation org ON member.id = org.id")
    List<Payment> findAllPaidMembers();
}
