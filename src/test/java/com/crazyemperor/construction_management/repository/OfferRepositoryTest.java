package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class OfferRepositoryTest {

    @Autowired
    private OfferRepository offerRepository;

    @Test
    void findByTitle_ReturnOfferByName_Success() {

//        given
        Offer expected = new Offer();
        Offer offer = new Offer();

        expected.setTitle("Skyscraper WC");
        expected.setAmount(new BigDecimal("10230000"));
        expected.setStatus(OfferStatus.ACCEPTED);

        offer.setTitle("Some house");
        offer.setAmount(new BigDecimal("100"));
        offer.setStatus(OfferStatus.ACCEPTED);

        offerRepository.save(expected);
        offerRepository.save(offer);


//        when
        Offer actual = offerRepository.findByTitle("Skyscraper WC");

//        then
        assertEquals(expected, actual);
    }

    @Test
    void findWithMembersByTitle_ReturnOfferAndMemberByName_Success() {

//        given
        Offer expected = new Offer();
        Offer offer = new Offer();
//        Member member = new Member();
//        Member memberTwo = new Member();
//        Set<MemberType> type = new HashSet<>();

//        member.setType(type);
//        member.getType().add(MemberType.CONSTRUCTOR);
//        member.setStatus(MemberStatus.ACTIVE);
        expected.setTitle("Skyscraper WC");
        expected.setAmount(new BigDecimal("10230000"));
        expected.setStatus(OfferStatus.ACCEPTED);
//        expected.setAcceptor(member);

//        memberTwo.setType(type);
//        memberTwo.getType().add(MemberType.ENGINEERING);
//        memberTwo.setStatus(MemberStatus.ACTIVE);
        offer.setTitle("Some house");
        offer.setAmount(new BigDecimal("100"));
        offer.setStatus(OfferStatus.ACCEPTED);
//        offer.setAcceptor(member);

        offerRepository.save(expected);
        offerRepository.save(offer);


//        when
        Offer actual = offerRepository.findByTitle("Skyscraper WC");

//        then
        assertEquals(expected, actual);
    }

    @Test
    void findAllActiveOffer_ReturnActiveOffer_Success() {

//        given
        long id = 1L;
        Offer offer = new Offer();
        Offer offerTwo = new Offer();

        offer.setTitle("Some house");
        offer.setAmount(new BigDecimal("100"));
        offer.setStatus(OfferStatus.ACCEPTED);

        offerTwo.setTitle("Skyscraper WC");
        offerTwo.setAmount(new BigDecimal("10230000"));
        offerTwo.setStatus(OfferStatus.ACCEPTED);

        offerRepository.save(offer);
        offerRepository.save(offerTwo);

//        when
        List<Offer> actual = offerRepository.findAllActiveOffer(id);

//        then
        assertEquals(2, actual.size());
    }

    @Test
    void findWithOfferAndMembers_ReturnOfferAndMember_Success() {

//        given
        Offer offer = new Offer();
        Offer offerTwo = new Offer();
        ConstructionSite constructionSite = new ConstructionSite();
        ConstructionSite constructionSiteTwo = new ConstructionSite();

        constructionSite.setTitle("Some house");
        constructionSite.setAmount(new BigDecimal("100"));
        constructionSite.setStatus(ConstructionSiteStatus.ACTIVE);
        offer.setOffer(constructionSite);
        offer.setTitle("Some house");
        offer.setAmount(new BigDecimal("100"));
        offer.setStatus(OfferStatus.ACCEPTED);

        constructionSiteTwo.setTitle("Skyscraper WC");
        constructionSiteTwo.setAmount(new BigDecimal("10230000"));
        constructionSiteTwo.setStatus(ConstructionSiteStatus.ACTIVE);
        offerTwo.setOffer(constructionSiteTwo);
        offerTwo.setTitle("Skyscraper WC");
        offerTwo.setAmount(new BigDecimal("10230000"));
        offerTwo.setStatus(OfferStatus.ACCEPTED);

        offerRepository.save(offer);
        offerRepository.save(offerTwo);

//        when
        List<Offer> actual = offerRepository.findWithOfferAndMembers();

//        then
        assertEquals(2, actual.size());
    }
}