package com.crazyemperor.construction_management.crud.organisation;

import com.crazyemperor.construction_management.entity.Organisation;

import java.util.List;

public interface OrganisationCRUDService {

    Organisation add(Organisation organisation);
    List<Organisation> getAllOrganisations();
    Organisation getByOrganisationID(long id);
    void deleteOrganisationByName(String name);
    void deleteOrganisationByID(long id);
}
