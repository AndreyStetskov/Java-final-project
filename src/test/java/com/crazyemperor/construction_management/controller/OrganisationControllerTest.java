package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.organisation.OrganisationCRUDService;
import com.crazyemperor.construction_management.entity.Organisation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OrganisationControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private OrganisationCRUDService organisationCRUDService;

    @InjectMocks
    private OrganisationController organisationController;


    @Test
    void createOrganisation_AddNewOrganisation_Success() {

//        given
        Organisation organisation = new Organisation();
        ResponseEntity<Organisation> expected = new ResponseEntity<>(organisation, HttpStatus.CREATED);

        when(organisationCRUDService.add(any())).thenReturn(expected.getBody());

//        when
        ResponseEntity<Organisation> actual = organisationController.createOrganisation(organisation);

//        then
        assertEquals(expected, actual);
        verify(organisationCRUDService, times(1)).add(organisation);
    }

    @Test
    void allOrganisations_ReturnListOfAllOrganisationsOfThreeObjects_Success() {

//        given
        List<Organisation> organisationList = new ArrayList<>();

        organisationList.add(new Organisation());
        organisationList.add(new Organisation());
        organisationList.add(new Organisation());

        ResponseEntity<List<Organisation>> expected = new ResponseEntity<>(organisationList, HttpStatus.OK);

        when(organisationCRUDService.getAllOrganisations()).thenReturn(organisationList);

//        when
        ResponseEntity<List<Organisation>> actual = organisationController.allOrganisations();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getOrganisationById_ReturnOrganisationById_Success() {

//        given
        Long idOrganisation = 10L;
        Organisation organisation = new Organisation();
        organisation.setId(idOrganisation);

        ResponseEntity<Organisation> expected = new ResponseEntity<>(organisation, HttpStatus.OK);

        when(organisationCRUDService.getOrganisationByID(idOrganisation)).thenReturn(organisation);

//        when
        ResponseEntity<Organisation> actual = organisationController.getOrganisationById(idOrganisation);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        Organisation organisation = new Organisation();
        organisation.setDeleted(true);

        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<Long> actual = organisationController.deleteByID(anyLong());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        Organisation organisation = new Organisation();
        organisation.setDeleted(true);

        ResponseEntity<String> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<String> actual = organisationController.deleteByName(anyString());

//        then
        assertEquals(expected, actual);
    }
}