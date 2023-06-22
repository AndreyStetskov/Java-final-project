package com.crazyemperor.construction_management.service.offer.implement;

import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.OfferStatus;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    @Transactional
    public Offer getCheapest() {

        List<Offer> offerList = offerRepository.findAll();

        if (offerList.isEmpty()) {
            return null;
        }

        AtomicReference<Offer> cheapestProposal = new AtomicReference<>(new Offer());

        AtomicReference<BigDecimal> minPrice = new AtomicReference<>(offerList.get(0).getAmount());

        offerList.stream()
                .filter(status -> status.getStatus().equals(OfferStatus.ACCEPTED))
                .filter(deleted -> !deleted.isDeleted())
                .forEach(offer -> {
                    if (minPrice.get().compareTo(offer.getAmount()) > 0) {
                        String value = String.valueOf(offer.getAmount());
                        minPrice.set(new BigDecimal(value));

                        cheapestProposal.set(offer);
                    }
                });

        return cheapestProposal.get();
    }

    @Override
    @Transactional
    public Offer getFastest() {

        List<Offer> offerList = offerRepository.findAll();

        if (offerList.isEmpty()) {
            return null;
        }

        AtomicReference<Offer> fastestProposal = new AtomicReference<>(new Offer());
        AtomicInteger minDays = new AtomicInteger(100000000);

        offerList.stream()
                .filter(status -> status.getStatus().equals(OfferStatus.ACCEPTED))
                .filter(deleted -> !deleted.isDeleted())
                .forEach(offer -> {
                    LocalDate start = LocalDate.parse(String.valueOf(offer.getStart()));
                    LocalDate finish = LocalDate.parse(String.valueOf(offer.getDeadline()));

                    int days = Period.between(start, finish).getDays();

                    if (days < minDays.get()) {
                        minDays.set(days);

                        fastestProposal.set(offer);
                    }
                });

        return fastestProposal.get();
    }

    @Override
    public List<Offer> getCheaperThan(BigDecimal amount) {

        List<Offer> offerList = offerRepository.findAll();

        if (offerList.isEmpty()) {
            return null;
        }

        offerList.stream()
                .filter(status -> status.getStatus().equals(OfferStatus.ACCEPTED))
                .filter(deleted -> !deleted.isDeleted())
                .filter(budget -> budget.getAmount().compareTo(amount) <= 0)
                .toList();

        return offerList;
    }

    @Override
    @Transactional
    public List<Offer> getFasterThen(int deadline) {
        List<Offer> offerList = offerRepository.findAll();

        if (offerList.isEmpty()) return null;

        List<Offer> newList = new ArrayList<>();

        offerList.stream()
                .filter(status -> status.getStatus().equals(OfferStatus.ACCEPTED))
                .filter(deleted -> !deleted.isDeleted())
                .forEach(offer -> {
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
