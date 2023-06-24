package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findByTitle(final String name);
    Invoice findById(final long id);
    @Query("SELECT invoice " +
            "FROM Invoice invoice " +
            "LEFT JOIN Member member ON invoice.id = member.id " +
            "WHERE invoice.isDeleted = false AND invoice.paidStatus = 1 AND invoice.payer = :memberId")
    List<Invoice> findAllUnpaid(Long memberId);
}
