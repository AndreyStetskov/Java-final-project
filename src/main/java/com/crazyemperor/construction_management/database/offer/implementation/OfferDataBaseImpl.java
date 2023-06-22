package com.crazyemperor.construction_management.database.offer.implementation;

import com.crazyemperor.construction_management.database.offer.OfferDataBaseService;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferDataBaseImpl implements OfferDataBaseService {

    private final OfferRepository offerRepository;


    @Override
    @CacheEvict("Offers")
    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    @Cacheable("Offers")
    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    @Override
    @Cacheable("Offers")
    public Offer getByID(long id) {
        return offerRepository.findById(id);
    }

    @Override
    @CacheEvict("Offers")
    public Offer deleteByName(String name, Offer delete) {
        Optional<Offer> invoiceOptional = Optional.ofNullable(offerRepository.findByTitle(name));
        if (invoiceOptional.isPresent()) {
            Offer offer = invoiceOptional.get();
            offer.setDeleted(true);
            offerRepository.save(offer);
        }
        return invoiceOptional.orElse(null);
    }

    @Override
    @CacheEvict("Offers")
    public Offer deleteByID(long id, Offer delete) {
        Optional<Offer> offerOptional = Optional.ofNullable(offerRepository.findById(id));
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setDeleted(true);
            offerRepository.save(offer);
        }
        return offerOptional.orElse(null);
    }
}
