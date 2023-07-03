package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.offer.OfferCRUDService;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferCRUDService offerCRUDService;
    private final OfferService offerService;


    @PostMapping(value = "/create-new-offer")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        offerCRUDService.add(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Offer>> allOffers() {
        List<Offer> offers = offerCRUDService.getAllOffers();

        if (offers != null && !offers.isEmpty()) {
            return ResponseEntity.ok(offers);
        } else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        Offer offer = offerCRUDService.getOfferByID(id);

        return offer != null ? ResponseEntity.ok(offer) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{name}")
    public ResponseEntity<Offer> getOfferByName(@PathVariable String name) {
        Offer offer = offerCRUDService.getOfferByTitle(name);

        return offer != null ? ResponseEntity.ok(offer) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable Long id) {
        offerCRUDService.deleteOfferByID(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        offerCRUDService.deleteOfferByName(name);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{memberId}/cheapest")
    public ResponseEntity<Offer> getTheBestOfferByAmount(@PathVariable long memberId) {
        Offer cheapestOffers = offerService.getCheapest(memberId);

        return cheapestOffers != null ? ResponseEntity.ok(cheapestOffers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{memberId}/cheapest/{amount}")
    public ResponseEntity<List<Offer>> getOffersByAmount(@PathVariable long memberId, @PathVariable BigDecimal amount) {
        List<Offer> cheapestOffers = offerService.getCheaperThan(memberId, amount);

        return cheapestOffers != null ? ResponseEntity.ok(cheapestOffers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{memberId}/fastest")
    public ResponseEntity<Offer> getTheBestOfferByDay(@PathVariable long memberId) {
        Offer fastestOffers = offerService.getFastest(memberId);

        return fastestOffers != null ? ResponseEntity.ok(fastestOffers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/fastest/{memberId}/{days}")
    public ResponseEntity<List<Offer>> getOffersByDay(@PathVariable long memberId, @PathVariable int days) {
        List<Offer> fastestOffers = offerService.getFasterThen(memberId, days);

        return fastestOffers != null ? ResponseEntity.ok(fastestOffers) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/deactivate")
    public void deactivate() {
        offerService.irrelevant();
    }
}
