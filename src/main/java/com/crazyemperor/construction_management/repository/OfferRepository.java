package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findByTitle(final String name);
    Offer findById(final long id);

    @Modifying
    @Query("UPDATE Offer offer " +
            "SET offer.status = 2 " +
            "WHERE offer.duration < LOCALTIME()")
    void deactivateExpired();
}
