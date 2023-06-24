package com.crazyemperor.construction_management.service.offer.implement;

import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.repository.OfferRepository;
import com.crazyemperor.construction_management.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    @Transactional
    public Offer getCheapest(long id) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);

        if (offerList.isEmpty()) {
            return null;
        }

        Offer cheapestProposal = new Offer();
        BigDecimal minPrice = new BigDecimal(String.valueOf(offerList.get(0).getAmount()));

        for (Offer offer : offerList) {
            if (minPrice.compareTo(offer.getAmount()) > 0) {
                String value = String.valueOf(offer.getAmount());
                minPrice= new BigDecimal(value);

                cheapestProposal= offer;
            }
        }

        return cheapestProposal;
    }

    @Override
    @Transactional
    public Offer getFastest(long id) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);

        if (offerList.isEmpty()) {
            return null;
        }

        Offer fastestProposal = new Offer();
        int minDays = Integer.MAX_VALUE;

        for (Offer offer : offerList) {
            LocalDate start = LocalDate.parse(String.valueOf(offer.getStart()));
            LocalDate finish = LocalDate.parse(String.valueOf(offer.getDeadline()));

            int days = Period.between(start, finish).getDays();

            if (days < minDays) {
                minDays = days;
                fastestProposal = offer;
            }
        }

        return fastestProposal;
    }

    @Override
    @Transactional
    public List<Offer> getCheaperThan(long id, BigDecimal amount) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);

        if (offerList.isEmpty()) {
            return null;
        }

        offerList.stream()
                .filter(budget -> budget.getAmount().compareTo(amount) <= 0)
                .collect(Collectors.toList());

        return offerList;
    }

    @Override
    @Transactional
    public List<Offer> getFasterThen(long id, int deadline) {
        List<Offer> offerList = offerRepository.findAllActiveOffer(id);

        if (offerList.isEmpty()) return null;

        List<Offer> newList = new ArrayList<>();

        offerList.forEach(offer -> {
                    LocalDate start = LocalDate.parse(String.valueOf(offer.getStart()));
                    LocalDate finish = LocalDate.parse(String.valueOf(offer.getDeadline()));
                    int days = Period.between(start, finish).getDays();

                    if (deadline > days) newList.add(offer);
                });

        return newList;
    }

    @Override
    @Transactional
    public void irrelevant() {
        offerRepository.deactivateExpired();
    }

}
