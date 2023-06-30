package com.crazyemperor.construction_management.database.offer;

import com.crazyemperor.construction_management.entity.Offer;

import java.util.List;

public interface OfferDataBaseService {

    Offer addOffer(Offer offer);
    List<Offer> getOffers();
    Offer getByID(long id);
    Offer getByTitle(String name);
    void deleteByName(String name);
    void deleteByID(long id);
}
