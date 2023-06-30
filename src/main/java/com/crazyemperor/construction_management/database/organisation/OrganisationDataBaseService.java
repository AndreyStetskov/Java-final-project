package com.crazyemperor.construction_management.database.organisation;

import com.crazyemperor.construction_management.entity.Organisation;

import java.util.List;

public interface OrganisationDataBaseService {

    Organisation addOrganisation(Organisation organisation);
    List<Organisation> getOrganisations();
    Organisation getByID(long id);
    void deleteByName(String name);
    void deleteByID(long id);
}
