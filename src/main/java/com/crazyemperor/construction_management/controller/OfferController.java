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


    @PostMapping(value = "/create_new_offer")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        offerCRUDService.add(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Offer>> allOffers() {
        List<Offer> offers = offerCRUDService.getAllOffers();

        return offers != null ? ResponseEntity.ok(offers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        Offer offer = offerCRUDService.getOfferByID(id);

        return offer != null ? ResponseEntity.ok(offer) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Offer> deleteByID(@PathVariable Long id, @RequestBody Offer offer) {
        Offer deleted = offerCRUDService.deleteOfferByID(id, offer);

        return offer != null ? ResponseEntity.ok(deleted) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<Offer> deleteByName(@PathVariable String name, @RequestBody Offer offer) {
        Offer deleted = offerCRUDService.deleteOfferByName(name, offer);

        return offer != null ? ResponseEntity.ok(deleted) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{member_id}/cheapest")
    public ResponseEntity<Offer> getTheBestOfferByAmount(@PathVariable long member_id) {
        Offer cheapestOffers = offerService.getCheapest(member_id);

        return cheapestOffers != null ? ResponseEntity.ok(cheapestOffers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{member_id}/cheapest/{amount}")
    public ResponseEntity<List<Offer>> getOffersByAmount(@PathVariable long member_id, @PathVariable BigDecimal amount) {
        List<Offer> cheapestOffers = offerService.getCheaperThan(member_id, amount);

        return cheapestOffers != null ? ResponseEntity.ok(cheapestOffers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{member_id}/fastest")
    public ResponseEntity<Offer> getTheBestOfferByDay(@PathVariable long member_id) {
        Offer fastestOffers = offerService.getFastest(member_id);

        return fastestOffers != null ? ResponseEntity.ok(fastestOffers) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/fastest/{member_id}/{days}")
    public ResponseEntity<List<Offer>> getOffersByDay(@PathVariable long member_id, @PathVariable int days) {
        List<Offer> fastestOffers = offerService.getFasterThen(member_id, days);

        return fastestOffers != null ? ResponseEntity.ok(fastestOffers) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/deactivate/")
    public void deactivate() {
        offerService.irrelevant();
    }
}
