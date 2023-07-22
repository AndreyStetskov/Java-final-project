package com.crazyemperor.construction_management.crud.offer.implementation;

import com.crazyemperor.construction_management.crud.offer.OfferCRUDService;
import com.crazyemperor.construction_management.database.offer.OfferDataBaseService;
import com.crazyemperor.construction_management.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfferCRUDServiceImpl implements OfferCRUDService {

    @Autowired
    private OfferDataBaseService offerDBService;


    @Override
    public Offer add(Offer offer) {
        return offerDBService.addOffer(offer);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerDBService.getOffers();
    }

    @Override
    public Offer getOfferByID(long id) {
        return offerDBService.getByID(id);
    }

    @Override
    public Offer getOfferByTitle(String name) {
        return offerDBService.getByTitle(name);
    }

    @Override
    public void deleteOfferByName(String name) {
        offerDBService.deleteByName(name);
    }

    @Override
    public void deleteOfferByID(long id) {
        offerDBService.deleteByID(id);
    }
}
