package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.building.ConstructionSiteCRUDService;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.MemberType;
import com.crazyemperor.construction_management.service.building.ConstructionSiteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConstructionSiteControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private ConstructionSiteCRUDService constructionSiteCRUDService;
    @Mock
    private ConstructionSiteService constructionSiteService;

    @InjectMocks
    private ConstructionSiteController constructionSiteController;

    private final ConstructionSite constructionSite = new ConstructionSite();
    private final Member member = new Member();
    private final Offer offer = new Offer();
    private final List<ConstructionSite> constructionSiteList = new ArrayList<>();
    private final Set<MemberType> memberTypes = new HashSet<>();


    @Test
    void createObject_AddNewConstructionSite_Success() {

//        given
        ResponseEntity<ConstructionSite> expected = new ResponseEntity<>(constructionSite, HttpStatus.CREATED);

//        when
        ResponseEntity<ConstructionSite> actual = constructionSiteController.createObject(constructionSite);

//        then
        assertEquals(expected, actual);
        verify(constructionSiteCRUDService, times(1)).add(constructionSite);
    }

    @Test
    void allConstructionSites_ReturnListOfAllConstructionSitesOfThreeObjects_Success() {

//        given
        constructionSiteList.add(new ConstructionSite());
        constructionSiteList.add(new ConstructionSite());
        constructionSiteList.add(new ConstructionSite());

        ResponseEntity<List<ConstructionSite>> expected = new ResponseEntity<>(constructionSiteList, HttpStatus.OK);

        when(constructionSiteCRUDService.getAllConstructionSites()).thenReturn(constructionSiteList);

//        when
        ResponseEntity<List<ConstructionSite>> actual = constructionSiteController.allConstructionSites();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getConstructionSiteById_ReturnConstructionSiteById_Success() {

//        given
        Long idBuilding = anyLong();
        constructionSite.setId(idBuilding);

        ResponseEntity<ConstructionSite> expected = new ResponseEntity<>(constructionSite, HttpStatus.OK);

        when(constructionSiteCRUDService.getConstructionSiteByID(idBuilding)).thenReturn(constructionSite);

//        when
        ResponseEntity<ConstructionSite> actual = constructionSiteController.getConstructionSiteById(idBuilding);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        constructionSite.setDeleted(true);
        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<Long> actual = constructionSiteController.deleteByID(anyLong());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        constructionSite.setDeleted(true);
        ResponseEntity<String> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<String> actual = constructionSiteController.deleteByName(anyString());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getAllSum_ReturnAmountFromAllBuildings_Success() {

//        given
        BigDecimal amount = new BigDecimal("115638887");
        ResponseEntity<BigDecimal> expected = new ResponseEntity<>(HttpStatus.OK);

        when(constructionSiteService.getSumAllBuildings()).thenReturn(amount);

//        when
        ResponseEntity<BigDecimal> actual = constructionSiteController.getAllSum();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getCurrentSum_ReturnAmountFromCurrentBuildings_Success() {

//        given
        BigDecimal amount = new BigDecimal("3211488");
        ResponseEntity<BigDecimal> expected = new ResponseEntity<>(HttpStatus.OK);

        when(constructionSiteService.getSumCurrentBuildings()).thenReturn(amount);

//        when
        ResponseEntity<BigDecimal> actual = constructionSiteController.getCurrentSum();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getSumAfter_ReturnAmountAfterSomeDate_Success() {

//        given
        BigDecimal amount = new BigDecimal("884268");
        ResponseEntity<BigDecimal> expected = new ResponseEntity<>(HttpStatus.OK);

        when(constructionSiteService.getSumBuildingsAfterDate(any(LocalDate.class))).thenReturn(amount);

//        when
        ResponseEntity<BigDecimal> actual = constructionSiteController.getSumAfter(any(LocalDate.class));

//        then
        assertEquals(expected, actual);
    }

    @Test
    void addConstructor_AddAMemberAsConstructor_Success() {

//        given
        member.setType(memberTypes);
        member.getType().add(MemberType.CONSTRUCTOR);
        constructionSite.setConstructor(member);

        ResponseEntity<ConstructionSite> expected = new ResponseEntity<>(constructionSite, HttpStatus.OK);

//        when
        ResponseEntity<ConstructionSite> actual = constructionSiteController.addConstructor(constructionSite, offer);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void addEngineering_AddAMemberAsEngineering_Success() {

//        given
        member.setType(memberTypes);
        member.getType().add(MemberType.ENGINEERING);
        constructionSite.setConstructor(member);

        ResponseEntity<ConstructionSite> expected = new ResponseEntity<>(constructionSite, HttpStatus.OK);

//        when
        ResponseEntity<ConstructionSite> actual = constructionSiteController.addConstructor(constructionSite, offer);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void addProjector_AddAMemberAsProjector_Success() {

//        given
        member.setType(memberTypes);
        member.getType().add(MemberType.PROJECTOR);
        constructionSite.setConstructor(member);

        ResponseEntity<ConstructionSite> expected = new ResponseEntity<>(constructionSite, HttpStatus.OK);

//        when
        ResponseEntity<ConstructionSite> actual = constructionSiteController.addConstructor(constructionSite, offer);

//        then
        assertEquals(expected, actual);
    }
}