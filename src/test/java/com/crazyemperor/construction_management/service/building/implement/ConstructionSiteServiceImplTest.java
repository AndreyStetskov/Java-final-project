package com.crazyemperor.construction_management.service.building.implement;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberType;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import com.crazyemperor.construction_management.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ConstructionSiteServiceImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private ConstructionSiteRepository constructionSiteRepository;
    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private ConstructionSiteServiceImpl constructionSiteService;


    @Test
    void getSumAllBuildings_ReturnAmountFromAllBuildings_Success() {

//        given
        List<ConstructionSite> buildingList = new ArrayList<>();

        ConstructionSite constructionSite = new ConstructionSite();
        constructionSite.setAmount(new BigDecimal("115638887"));

        ConstructionSite constructionSiteTwo = new ConstructionSite();
        constructionSiteTwo.setAmount(new BigDecimal("45234"));

        buildingList.add(constructionSite);
        buildingList.add(constructionSiteTwo);

        BigDecimal expected = new BigDecimal("115684121");

        when(constructionSiteRepository.findAll()).thenReturn(buildingList);

//        when
        BigDecimal actual = constructionSiteService.getSumAllBuildings();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getSumCurrentBuildings() {

//        given
        List<ConstructionSite> buildingList = new ArrayList<>();

        ConstructionSite constructionSite = new ConstructionSite();
        constructionSite.setAmount(new BigDecimal("115638887"));
        constructionSite.setStatus(ConstructionSiteStatus.ACTIVE);

        ConstructionSite constructionSiteTwo = new ConstructionSite();
        constructionSiteTwo.setAmount(new BigDecimal("45234"));
        constructionSiteTwo.setStatus(ConstructionSiteStatus.CLOSED);

        buildingList.add(constructionSite);
        buildingList.add(constructionSiteTwo);

        BigDecimal expected = new BigDecimal("115638887");

        when(constructionSiteRepository.findAll()).thenReturn(buildingList);

//        when
        BigDecimal actual = constructionSiteService.getSumCurrentBuildings();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getSumBuildingsAfterDate() {

//        given
        List<ConstructionSite> buildingList = new ArrayList<>();
        LocalDate date = LocalDate.of(2000, 1, 1);

        ConstructionSite constructionSite = new ConstructionSite();
        constructionSite.setStart(LocalDate.parse("2022-02-05"));
        constructionSite.setAmount(new BigDecimal("115638887"));
        constructionSite.setStatus(ConstructionSiteStatus.ACTIVE);

        ConstructionSite constructionSiteTwo = new ConstructionSite();
        constructionSiteTwo.setStart(LocalDate.parse("1287-02-05"));
        constructionSiteTwo.setAmount(new BigDecimal("45234"));
        constructionSiteTwo.setStatus(ConstructionSiteStatus.ACTIVE);

        buildingList.add(constructionSite);
        buildingList.add(constructionSiteTwo);

        BigDecimal expected = new BigDecimal("115638887");

        when(constructionSiteRepository.findAll()).thenReturn(buildingList);

//        when
        BigDecimal actual = constructionSiteService.getSumBuildingsAfterDate(date);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void selectedConstructor() {

//        given
        ConstructionSite expected = new ConstructionSite();
        ConstructionSite actual = new ConstructionSite();
        Member member = new Member();
        Offer offer = new Offer();
        String name = anyString();
        Set<MemberType> type = new HashSet<>();

        member.setType(type);
        member.getType().add(MemberType.CONSTRUCTOR);
        member.setStatus(MemberStatus.ACTIVE);

        expected.setStatus(ConstructionSiteStatus.ACTIVE);
        expected.setConstructor(member);

        actual.setStatus(ConstructionSiteStatus.ACTIVE);

        when(offerRepository.findWithMembersByTitle(name)).thenReturn(offer);
        when(constructionSiteRepository.findByTitle(name)).thenReturn(expected);

        //        when
        constructionSiteService.selectedConstructor(expected, offer);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void selectedEngineering() {

//        given
        ConstructionSite expected = new ConstructionSite();
        ConstructionSite actual = new ConstructionSite();
        Member member = new Member();
        Offer offer = new Offer();
        String name = anyString();
        Set<MemberType> type = new HashSet<>();

        member.setType(type);
        member.getType().add(MemberType.ENGINEERING);
        member.setStatus(MemberStatus.ACTIVE);

        expected.setStatus(ConstructionSiteStatus.ACTIVE);
        expected.setConstructor(member);

        actual.setStatus(ConstructionSiteStatus.ACTIVE);

        when(offerRepository.findWithMembersByTitle(name)).thenReturn(offer);
        when(constructionSiteRepository.findByTitle(name)).thenReturn(expected);

        //        when
        constructionSiteService.selectedConstructor(expected, offer);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void selectedProjector() {

//        given
        ConstructionSite expected = new ConstructionSite();
        ConstructionSite actual = new ConstructionSite();
        Member member = new Member();
        Offer offer = new Offer();
        String name = anyString();
        Set<MemberType> type = new HashSet<>();

        member.setType(type);
        member.getType().add(MemberType.PROJECTOR);
        member.setStatus(MemberStatus.ACTIVE);

        expected.setStatus(ConstructionSiteStatus.ACTIVE);
        expected.setConstructor(member);

        actual.setStatus(ConstructionSiteStatus.ACTIVE);

        when(offerRepository.findWithMembersByTitle(name)).thenReturn(offer);
        when(constructionSiteRepository.findByTitle(name)).thenReturn(expected);

        //        when
        constructionSiteService.selectedConstructor(expected, offer);

//        then
        assertEquals(expected, actual);
    }
}