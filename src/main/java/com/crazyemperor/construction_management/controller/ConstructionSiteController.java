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


    @PostMapping(value = "/create_new_object")
    public ResponseEntity<ConstructionSite> createObject(@RequestBody ConstructionSite constructionSite) {
        constructionSiteCRUDService.add(constructionSite);
        return ResponseEntity.status(HttpStatus.CREATED).body(constructionSite);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<ConstructionSite>> allConstructionSites() {
        List<ConstructionSite> constructionSites = constructionSiteCRUDService.getAllConstructionSites();

        return constructionSites != null ? ResponseEntity.ok(constructionSites) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ConstructionSite> getConstructionSiteById(@PathVariable Long id) {
        ConstructionSite constructionSite = constructionSiteCRUDService.getConstructionSiteByID(id);

        return constructionSite != null ? ResponseEntity.ok(constructionSite) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<ConstructionSite> deleteByID(@PathVariable Long id, @RequestBody ConstructionSite constructionSite) {
        ConstructionSite closeConstructionSite = constructionSiteCRUDService.deleteConstructionSiteByID(id, constructionSite);

        return constructionSite != null ? ResponseEntity.ok(closeConstructionSite) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<ConstructionSite> deleteByName(@PathVariable String name, @RequestBody ConstructionSite constructionSite) {
        ConstructionSite closeConstructionSite = constructionSiteCRUDService.deleteConstructionSiteByName(name, constructionSite);

        return constructionSite != null ? ResponseEntity.ok(closeConstructionSite) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/sum/all")
    public BigDecimal getAllSum() {
        return constructionSiteService.getSumAllBuildings();
    }

    @GetMapping(value = "sum/current")
    public BigDecimal getCurrentSum() {
        return constructionSiteService.getSumCurrentBuildings();
    }

    @GetMapping(value = "sum/{date}")
    public BigDecimal getSumAfter(@RequestBody LocalDate date) {
        return constructionSiteService.getSumBuildingsAfterDate(date);
    }

    @PutMapping(value = "/add_constructor")
    public ResponseEntity<ConstructionSite> addConstructor(@RequestBody ConstructionSite constructionSite, @RequestBody Offer offer) {
        ConstructionSite constructor = constructionSiteService.selectedConstructor(constructionSite, offer);

        return constructionSite != null ? ResponseEntity.ok(constructor) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/add_engineering")
    public ResponseEntity<ConstructionSite> addEngineering(@RequestBody ConstructionSite constructionSite, @RequestBody Offer offer) {
        ConstructionSite engineering = constructionSiteService.selectedEngineering(constructionSite, offer);

        return constructionSite != null ? ResponseEntity.ok(engineering) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/add_protector")
    public ResponseEntity<ConstructionSite> addProtector(@RequestBody ConstructionSite constructionSite, @RequestBody Offer offer) {
        ConstructionSite protector = constructionSiteService.selectedProtector(constructionSite, offer);

        return constructionSite != null ? ResponseEntity.ok(protector) : ResponseEntity.noContent().build();
    }
}
