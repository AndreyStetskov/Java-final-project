package com.crazyemperor.construction_management.service.offer;

import com.crazyemperor.construction_management.entity.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService {

    Offer getCheapest(long id);
    Offer getFastest(long id);

    List<Offer> getCheaperThan(long id, BigDecimal amount);
    List<Offer> getFasterThen(long id, int days);

    void irrelevant ();
}
