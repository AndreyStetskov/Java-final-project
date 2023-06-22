package com.crazyemperor.construction_management.database.organisation;

import com.crazyemperor.construction_management.entity.Organisation;

import java.util.List;

public interface OrganisationDataBaseService {

    Organisation addOrganisation(Organisation organisation);
    List<Organisation> getOrganisations();
    Organisation getByID(long id);
    Organisation deleteByName(String name, Organisation organisation);
    Organisation deleteByID(long id, Organisation organisation);
}
