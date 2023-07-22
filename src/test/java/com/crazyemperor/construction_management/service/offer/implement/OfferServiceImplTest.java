package com.crazyemperor.construction_management.service.offer.implement;

import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.OfferStatus;
import com.crazyemperor.construction_management.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OfferServiceImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    @Test
    void getCheapest_ReturnOfferWithMinimumAmount_Success() {

//        given
        long id = anyLong();
        Offer offer = new Offer();
        Offer expected = new Offer();
        List<Offer> offerList = new ArrayList<>();

        offer.setStatus(OfferStatus.ACCEPTED);
        offer.setAmount(new BigDecimal("115638887"));

        expected.setStatus(OfferStatus.ACCEPTED);
        expected.setAmount(new BigDecimal("45234"));

        offerList.add(offer);
        offerList.add(expected);

        when(offerRepository.findAllActiveOffer(id)).thenReturn(offerList);

//        when
        Offer actual = offerService.getCheapest(id);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getFastest_ReturnOfferWithMinimumDay_Success() {

//        given
        long id = anyLong();
        Offer offer = new Offer();
        Offer expected = new Offer();
        List<Offer> offerList = new ArrayList<>();

        offer.setStatus(OfferStatus.ACCEPTED);
        offer.setStart(LocalDate.of(2020, 5, 10));
        offer.setDeadline(LocalDate.of(2020, 7, 5));

        expected.setStatus(OfferStatus.ACCEPTED);
        expected.setStart(LocalDate.of(2000, 12, 5));
        expected.setDeadline(LocalDate.of(2001, 1, 5));

        offerList.add(offer);
        offerList.add(expected);

        when(offerRepository.findAllActiveOffer(id)).thenReturn(offerList);

//        when
        Offer actual = offerService.getFastest(id);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getCheaperThan_ReturnOfferWithMinimumAmountLessSomeAmount_Success() {

//        given
        long id = anyLong();
        BigDecimal amount = any(BigDecimal.class);
        Offer offer = new Offer();
        Offer offerTwo = new Offer();
        List<Offer> expected = new ArrayList<>();

        offer.setStatus(OfferStatus.ACCEPTED);
        offer.setAmount(new BigDecimal("115638887"));

        offerTwo.setStatus(OfferStatus.ACCEPTED);
        offerTwo.setAmount(new BigDecimal("45234"));

        expected.add(offer);
        expected.add(offerTwo);

        when(offerRepository.findAllActiveOffer(id)).thenReturn(expected);

//        when
        List<Offer> actual = offerService.getCheaperThan(id, amount);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getFasterThenReturnOfferWithMinimumAmountLessSomeDate_Success() {

//        given
        long id = anyLong();
        int deadline = anyInt();
        Offer offer = new Offer();
        Offer offerTwo = new Offer();
        List<Offer> expected = new ArrayList<>();

        offer.setStatus(OfferStatus.ACCEPTED);
        offer.setStart(LocalDate.of(2020, 5, 10));
        offer.setDeadline(LocalDate.of(2020, 7, 5));

        offerTwo.setStatus(OfferStatus.ACCEPTED);
        offerTwo.setStart(LocalDate.of(2000, 12, 5));
        offerTwo.setDeadline(LocalDate.of(2001, 1, 5));

        expected.add(offer);
        expected.add(offerTwo);

        when(offerRepository.findAllActiveOffer(id)).thenReturn(expected);

//        when
        List<Offer> actual = offerService.getFasterThen(id, deadline);

//        then
        assertEquals(expected, actual);
    }
}