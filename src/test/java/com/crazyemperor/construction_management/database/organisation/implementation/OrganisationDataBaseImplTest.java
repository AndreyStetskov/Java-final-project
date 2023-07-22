package com.crazyemperor.construction_management.database.organisation.implementation;

import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.repository.OrganisationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OrganisationDataBaseImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private OrganisationRepository organisationRepository;

    @InjectMocks
    private OrganisationDataBaseImpl organisationDB;


    @Test
    void addOrganisation_AddNewOrganisation_Success() {

//        given
        Organisation expected = new Organisation();

//        when
        organisationDB.addOrganisation(expected);

//        then
        verify(organisationRepository, times(1)).save(expected);
    }

    @Test
    void getOrganisations_ReturnListOfAllOrganisationsOfThreeObjects_Success() {

//        given
        List<Organisation> expected = new ArrayList<>();
        expected.add(new Organisation());
        expected.add(new Organisation());
        expected.add(new Organisation());

        when(organisationRepository.findAll()).thenReturn(expected);

//        when
        List<Organisation> actual = organisationDB.getOrganisations();

//        then
        assertEquals(expected, actual);
        verify(organisationRepository, times(1)).findAll();
    }

    @Test
    void getByID_ReturnOrganisationById_Success() {

//        given
        Organisation expected = new Organisation();

        when(organisationRepository.findById(anyLong())).thenReturn(Optional.of(expected));

//        when
        Optional<Organisation> actualOptional = organisationRepository.findById(anyLong());
        Organisation actual = actualOptional.get();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByName_ChangeDeleteStatusToTrue_Success() {

//        given
        String name = anyString();
        Organisation actual = new Organisation();
        actual.setName(name);
        actual.setDeleted(false);
        Organisation expected = new Organisation();
        expected.setName(name);
        expected.setDeleted(true);

        when(organisationRepository.findByName(name)).thenReturn(actual);

//        when
        organisationDB.deleteByName(actual.getName());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        Long id = anyLong();

        Optional<Organisation> optional = Optional.ofNullable(new Organisation());
        Organisation actual = optional.get();
        actual.setId(id);
        actual.setDeleted(false);

        Organisation expected = new Organisation();
        expected.setId(id);
        expected.setDeleted(true);

        when(organisationRepository.findById(id)).thenReturn(optional);

//        when
        organisationDB.deleteByID(actual.getId());

//        then
        assertEquals(expected, actual);
    }
}