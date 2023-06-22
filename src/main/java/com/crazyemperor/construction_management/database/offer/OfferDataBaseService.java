package com.crazyemperor.construction_management.database.offer;

import com.crazyemperor.construction_management.entity.Offer;

import java.util.List;

public interface OfferDataBaseService {

    Offer addOffer(Offer offer);
    List<Offer> getOffers();
    Offer getByID(long id);
    Offer deleteByName(String name, Offer delete);
    Offer deleteByID(long id, Offer delete);
}
