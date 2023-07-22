package com.crazyemperor.construction_management.database.offer.implementation;

import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OfferDataBaseImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferDataBaseImpl offerDB;


    @Test
    void addOffer_AddNewOffer_Success() {

//        given
        Offer expected = new Offer();

//        when
        offerDB.addOffer(expected);

//        then
        verify(offerRepository, times(1)).save(expected);
    }

    @Test
    void getOffers_ReturnListOfAllOffersOfThreeObjects_Success() {

//        given
        List<Offer> expected = new ArrayList<>();
        expected.add(new Offer());
        expected.add(new Offer());
        expected.add(new Offer());

        when(offerRepository.findAll()).thenReturn(expected);

//        when
        List<Offer> actual = offerDB.getOffers();

//        then
        assertEquals(expected, actual);
        verify(offerRepository, times(1)).findAll();
    }

    @Test
    void getByID_ReturnOfferById_Success() {

//        given
        Offer expected = new Offer();

        when(offerRepository.findById(anyLong())).thenReturn(Optional.of(expected));

//        when
        Optional<Offer> actualOptional = offerRepository.findById(anyLong());
        Offer actual = actualOptional.get();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getByTitle() {

//        given
        Offer expected = new Offer();

        when(offerRepository.findByTitle(anyString())).thenReturn(expected);

//        when
        Offer actual = offerRepository.findByTitle(anyString());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        String name = anyString();
        Offer actual = new Offer();
        actual.setTitle(name);
        actual.setDeleted(false);
        Offer expected = new Offer();
        expected.setTitle(name);
        expected.setDeleted(true);

        when(offerRepository.findByTitle(name)).thenReturn(actual);

//        when
        offerDB.deleteByName(actual.getTitle());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        Long id = anyLong();

        Optional<Offer> optional = Optional.ofNullable(new Offer());
        Offer actual = optional.get();
        actual.setId(id);
        actual.setDeleted(false);

        Offer expected = new Offer();
        expected.setId(id);
        expected.setDeleted(true);

        when(offerRepository.findById(id)).thenReturn(optional);

//        when
        offerDB.deleteByID(actual.getId());

//        then
        assertEquals(expected, actual);
    }
}