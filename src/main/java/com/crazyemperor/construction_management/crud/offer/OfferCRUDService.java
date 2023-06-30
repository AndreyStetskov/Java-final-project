package com.crazyemperor.construction_management.crud.offer;

import com.crazyemperor.construction_management.entity.Offer;

import java.util.List;

public interface OfferCRUDService {

    Offer add(Offer offer);
    List<Offer> getAllOffers();
    Offer getOfferByID(long id);
    Offer getOfferByTitle(String name);
    void deleteOfferByName(String name);
    void deleteOfferByID(long id);
}
