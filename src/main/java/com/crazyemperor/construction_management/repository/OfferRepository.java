package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findByTitle(final String name);
    @Query("SELECT offer " +
            "FROM Offer offer " +
            "LEFT JOIN Member member ON offer.id = member.id " +
            "WHERE offer.title = :name")
    Offer findWithMembersByTitle(final String name);

    @Query("SELECT offer " +
            "FROM Offer offer " +
            "LEFT JOIN Member member ON offer.id = member.id " +
            "WHERE offer.isDeleted = false AND offer.status = 0 AND offer.offerer = :memberId")
    List<Offer> findAllActiveOffer(long memberId);

    @Query("SELECT offer " +
            "FROM Offer offer " +
            "RIGHT JOIN ConstructionSite building ON offer.id = building.id " +
            "WHERE building.finish < LOCALTIME()")
    List<Offer> findWithOfferAndMembers();

    @Modifying
    @Query("UPDATE Offer offer " +
            "SET offer.status = 3 " +
            "WHERE offer.duration < LOCALTIME()")
    void deactivateExpired();
}
