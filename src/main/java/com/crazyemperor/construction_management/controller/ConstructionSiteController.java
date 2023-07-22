package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.building.ConstructionSiteCRUDService;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.service.building.ConstructionSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class ConstructionSiteController {

    private final ConstructionSiteCRUDService constructionSiteCRUDService;
    private final ConstructionSiteService constructionSiteService;


    @PostMapping(value = "/create-new-object")
    public ResponseEntity<ConstructionSite> createObject(@RequestBody ConstructionSite constructionSite) {
        constructionSiteCRUDService.add(constructionSite);
        return ResponseEntity.status(HttpStatus.CREATED).body(constructionSite);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<ConstructionSite>> allConstructionSites() {
        List<ConstructionSite> constructionSites = constructionSiteCRUDService.getAllConstructionSites();

        if (constructionSites != null && !constructionSites.isEmpty()) {
            return ResponseEntity.ok(constructionSites);
        } else return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ConstructionSite> getConstructionSiteById(@PathVariable Long id) {
        ConstructionSite constructionSite = constructionSiteCRUDService.getConstructionSiteByID(id);

        return constructionSite != null ? ResponseEntity.ok(constructionSite) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable Long id) {
       constructionSiteCRUDService.deleteConstructionSiteByID(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        constructionSiteCRUDService.deleteConstructionSiteByName(name);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/sum/all")
    public ResponseEntity<BigDecimal> getAllSum() {
        constructionSiteService.getSumAllBuildings();

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "sum/current")
    public ResponseEntity<BigDecimal> getCurrentSum() {
        constructionSiteService.getSumCurrentBuildings();

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "sum/{date}")
    public ResponseEntity<BigDecimal> getSumAfter(@RequestBody LocalDate date) {
        constructionSiteService.getSumBuildingsAfterDate(date);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/add-constructor")
    public ResponseEntity<ConstructionSite> addConstructor(@RequestBody ConstructionSite constructionSite, @RequestBody Offer offer) {
        constructionSiteService.selectedConstructor(constructionSite, offer);

        return constructionSite != null ? ResponseEntity.ok(constructionSite) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/add-engineering")
    public ResponseEntity<ConstructionSite> addEngineering(@RequestBody ConstructionSite constructionSite, @RequestBody Offer offer) {
        constructionSiteService.selectedEngineering(constructionSite, offer);

        return constructionSite != null ? ResponseEntity.ok(constructionSite) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/add-protector")
    public ResponseEntity<ConstructionSite> addProjector(@RequestBody ConstructionSite constructionSite, @RequestBody Offer offer) {
        constructionSiteService.selectedProjector(constructionSite, offer);

        return constructionSite != null ? ResponseEntity.ok(constructionSite) : ResponseEntity.badRequest().build();
    }
}
