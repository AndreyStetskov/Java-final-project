package com.crazyemperor.construction_management.database.offer.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
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
public class OfferDataBaseImpl implements com.crazyemperor.construction_management.database.offer.OfferDataBaseService {

    private final OfferRepository offerRepository;


    @Override
    @CacheEvict("offers")
    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    @Cacheable("offers")
    public List<Offer> getOffers() {
        Optional<List<Offer>> offers = Optional.of(offerRepository.findAll());

        return offers
                .orElseThrow(() -> {
                    new NoDataFoundException("No one offer found");
                    return null;
                });
    }

    @Override
    @Cacheable("offers")
    public Offer getByID(long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No offer found for id %d", id));
                    return null;
                });
    }

    @Override
    @Cacheable("offers")
    public Offer getByTitle(String name) {
        Optional<Offer> offers = Optional.ofNullable(offerRepository.findByTitle(name));

        return offers
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No offer found for name %ы", name));
                    return null;
                });
    }

    @Override
    @CacheEvict("offers")
    public void deleteByName(String name) {
        Optional<Offer> offerOptional = Optional.ofNullable(offerRepository.findByTitle(name));
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setDeleted(true);
            offerRepository.save(offer);
        }
        else try {
            throw new NoDataFoundException("This offer didn't find");
        } catch (NoDataFoundException e) {
            throw new IllegalArgumentException("NoDataFoundException didn't work", e);
        }
    }

    @Override
    @CacheEvict("offers")
    public void deleteByID(long id) {
        Offer offers =offerRepository.findById(id)
                .orElseThrow(() -> {
                    try {
                        throw new NoDataFoundException(String.format("No offer found for oid %d", id));
                    } catch (NoDataFoundException e) {
                        throw new IllegalArgumentException("NoDataFoundException didn't work", e);
                    }
                });

            offers.setDeleted(true);
            offerRepository.save(offers);
    }
}
