package com.crazyemperor.construction_management.service.offer.implement;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.repository.OfferRepository;
import com.crazyemperor.construction_management.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @SneakyThrows
    @Override
    @Transactional
    public Offer getCheapest(long id) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);
        if (offerList.isEmpty()) throw new NoDataFoundException("There's no offer which was send to you");

        Offer cheapestProposal = new Offer();
        BigDecimal minPrice = new BigDecimal(String.valueOf(offerList.get(0).getAmount()));

        for (Offer offer : offerList) {
            if (minPrice.compareTo(offer.getAmount()) > 0) {
                String value = String.valueOf(offer.getAmount());
                minPrice = new BigDecimal(value);

                cheapestProposal = offer;
            }
        }

        return cheapestProposal;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Offer getFastest(long id) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);
        if (offerList.isEmpty()) throw new NoDataFoundException("There's no offer which was send to you");

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

    @SneakyThrows
    @Override
    @Transactional
    public List<Offer> getCheaperThan(long id, BigDecimal amount) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);
        if (offerList.isEmpty()) throw new NoDataFoundException("There's no offer which was send to you");

        List<Offer> newList = new ArrayList<>();

        for (Offer offer : offerList) {
            if (offer.getAmount().compareTo(amount) >= 0) {
                newList.add(offer);
            }
        }
        return newList;
    }

    @SneakyThrows
    @Override
    @Transactional
    public List<Offer> getFasterThen(long id, int deadline) {

        List<Offer> offerList = offerRepository.findAllActiveOffer(id);
        if (offerList.isEmpty()) throw new NoDataFoundException("There's no offer which was send to you");

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
