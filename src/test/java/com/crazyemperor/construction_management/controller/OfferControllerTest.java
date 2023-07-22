package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.offer.OfferCRUDService;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.service.offer.OfferService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OfferControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private OfferCRUDService offerCRUDService;
    @Mock
    private OfferService offerService;

    @InjectMocks
    private OfferController offerController;

    private final Offer offer = new Offer();
    private final List<Offer> offerList = new ArrayList<>();


    @Test
    void createOffer_AddNewOffer_Success() {

//        given
        ResponseEntity<Offer> expected = new ResponseEntity<>(offer, HttpStatus.CREATED);

        when(offerCRUDService.add(any())).thenReturn(expected.getBody());

//        when
        ResponseEntity<Offer> actual = offerController.createOffer(offer);

//        then
        assertEquals(expected, actual);
        verify(offerCRUDService, times(1)).add(offer);
    }

    @Test
    void allOffers_ReturnListOfAllOffersOfThreeObjects_Success() {

//        given
        offerList.add(new Offer());
        offerList.add(new Offer());
        offerList.add(new Offer());

        ResponseEntity<List<Offer>> expected = new ResponseEntity<>(offerList, HttpStatus.OK);

        when(offerCRUDService.getAllOffers()).thenReturn(offerList);

//        when
        ResponseEntity<List<Offer>> actual = offerController.allOffers();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getOfferById_ReturnOfferById_Success() {

//        given
        Long idOffer = anyLong();
        offer.setId(idOffer);

        ResponseEntity<Offer> expected = new ResponseEntity<>(offer, HttpStatus.OK);

        when(offerCRUDService.getOfferByID(idOffer)).thenReturn(offer);

//        when
        ResponseEntity<Offer> actual = offerController.getOfferById(idOffer);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        offer.setDeleted(true);

        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<Long> actual = offerController.deleteByID(anyLong());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        offer.setDeleted(true);

        ResponseEntity<String> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<String> actual = offerController.deleteByName(anyString());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getTheBestOfferByAmount_ReturnOfferWithTheCheapestAmount_Success() {

//        given
        long idMember = anyLong();

        ResponseEntity<Offer> expected = new ResponseEntity<>(offer, HttpStatus.OK);

        when(offerService.getCheapest(idMember)).thenReturn(offer);

//        when
        ResponseEntity<Offer> actual = offerController.getTheBestOfferByAmount(idMember);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getOffersByAmount_ReturnListOfAllOffersOfThreeObjectsWithCheaperAmount_Success() {

//        given
        long idMember = anyLong();
        BigDecimal amount = any(BigDecimal.class);

        offerList.add(new Offer());
        offerList.add(new Offer());
        offerList.add(new Offer());

        ResponseEntity<List<Offer>> expected = new ResponseEntity<>(offerList, HttpStatus.OK);

        when(offerService.getCheaperThan(idMember, amount)).thenReturn(offerList);

//        when
        ResponseEntity<List<Offer>> actual = offerController.getOffersByAmount(idMember, amount);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getTheBestOfferByDay_ReturnOfferWithMinimumOfDays_Success() {

//        given
        long idMember = anyLong();

        ResponseEntity<Offer> expected = new ResponseEntity<>(offer, HttpStatus.OK);

        when(offerService.getFastest(idMember)).thenReturn(offer);

//        when
        ResponseEntity<Offer> actual = offerController.getTheBestOfferByDay(idMember);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getOffersByDay_ReturnListOfAllOffersOfThreeObjectsWithFasterDay_Success() {

//        given
        long idMember = anyLong();
        int date = anyInt();

        offerList.add(new Offer());
        offerList.add(new Offer());
        offerList.add(new Offer());

        ResponseEntity<List<Offer>> expected = new ResponseEntity<>(offerList, HttpStatus.OK);

        when(offerService.getFasterThen(idMember, date)).thenReturn(offerList);

//        when
        ResponseEntity<List<Offer>> actual = offerController.getOffersByDay(idMember, date);

//        then
        assertEquals(expected, actual);
    }
}