package com.crazyemperor.construction_management.crud.organisation.implementation;

import com.crazyemperor.construction_management.database.organisation.OrganisationDataBaseService;
import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.crud.organisation.OrganisationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrganisationCRUDServiceImpl implements OrganisationCRUDService {

    @Autowired
    private OrganisationDataBaseService organisationDB;


    @Override
    public Organisation add(Organisation organisation) {
        return organisationDB.addOrganisation(organisation);
    }

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationDB.getOrganisations();
    }

    @Override
    public Organisation getByOrganisationID(long id) {
        return organisationDB.getByID(id);
    }

    @Override
    public Organisation deleteOrganisationByName(String name, Organisation organisation) {
        return organisationDB.deleteByName(name, organisation);
    }

    @Override
    public Organisation deleteOrganisationByID(long id, Organisation organisation) {
        return organisationDB.deleteByID(id, organisation);
    }
}
