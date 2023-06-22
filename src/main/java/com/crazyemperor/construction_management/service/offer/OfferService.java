package com.crazyemperor.construction_management.service.offer;

import com.crazyemperor.construction_management.entity.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService {

    Offer getCheapest();
    Offer getFastest();

    List<Offer> getCheaperThan(BigDecimal amount);
    List<Offer> getFasterThen(int days);

    void irrelevant ();
}
