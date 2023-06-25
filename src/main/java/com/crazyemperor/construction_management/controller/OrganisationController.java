package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.organisation.OrganisationCRUDService;
import com.crazyemperor.construction_management.entity.Organisation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organisation")
@RequiredArgsConstructor
public class OrganisationController {

    private final OrganisationCRUDService organisationCRUDService;


    @PostMapping(value = "/create_new_organisation")
    public ResponseEntity<Organisation> createOffer(@RequestBody Organisation organisation) {
        organisationCRUDService.add(organisation);
        return ResponseEntity.status(HttpStatus.CREATED).body(organisation);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Organisation>> allOffers() {
        List<Organisation> organisations = organisationCRUDService.getAllOrganisations();

        if (organisations != null && !organisations.isEmpty()) {
            return ResponseEntity.ok(organisations);
        }
        else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Organisation> getOrganisationById(@PathVariable Long id) {
        Organisation organisation = organisationCRUDService.getByOrganisationID(id);

        return organisation != null ? ResponseEntity.ok(organisation) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable Long id) {
        organisationCRUDService.deleteOrganisationByID(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/delete/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        organisationCRUDService.deleteOrganisationByName(name);

        return ResponseEntity.ok().build();
    }
}
