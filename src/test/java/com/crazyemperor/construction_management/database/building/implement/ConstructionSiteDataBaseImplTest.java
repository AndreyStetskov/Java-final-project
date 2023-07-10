package com.crazyemperor.construction_management.database.building.implement;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConstructionSiteDataBaseImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private ConstructionSiteRepository constructionSiteRepository;

    @InjectMocks
    private ConstructionSiteDataBaseImpl constructionSiteDB;


    @Test
    void addConstructionSite_AddNewConstructionSite_Success() {

//        given
        ConstructionSite expected = new ConstructionSite();

//        when
        constructionSiteDB.addConstructionSite(expected);

//        then
        verify(constructionSiteRepository, times(1)).save(expected);
    }

    @Test
    void getConstructionSites() {

//        given
        List<ConstructionSite> expected = new ArrayList<>();

        when(constructionSiteRepository.findAll()).thenReturn(expected);

//        when
        List<ConstructionSite> actual = constructionSiteDB.getConstructionSites();

//        then
        assertEquals(expected, actual);
        verify(constructionSiteRepository, times(1)).findAll();
    }

    @Test
    void getByID() {

//        given
        ConstructionSite expected = new ConstructionSite();

        when(constructionSiteRepository.findById(anyLong())).thenReturn(Optional.of(expected));

//        when
        Optional<ConstructionSite> actualOptional = constructionSiteRepository.findById(anyLong());
        ConstructionSite actual = actualOptional.get();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName() {

//        given
        String name = anyString();
        ConstructionSite actual = new ConstructionSite();
        actual.setTitle(name);
        actual.setDeleted(false);
        ConstructionSite expected = new ConstructionSite();
        expected.setTitle(name);
        expected.setDeleted(true);

        when(constructionSiteRepository.findByTitle(name)).thenReturn(actual);

//        when
        constructionSiteDB.deleteByName(actual.getTitle());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID() {

//        given
        Long id = anyLong();

        Optional<ConstructionSite> optional = Optional.ofNullable(new ConstructionSite());
        ConstructionSite actual = optional.get();
        actual.setId(id);
        actual.setDeleted(false);

        ConstructionSite expected = new ConstructionSite();
        expected.setId(id);
        expected.setDeleted(true);

        when(constructionSiteRepository.findById(id)).thenReturn(optional);

//        when
        constructionSiteDB.deleteByID(actual.getId());

//        then
        assertEquals(expected, actual);
    }
}