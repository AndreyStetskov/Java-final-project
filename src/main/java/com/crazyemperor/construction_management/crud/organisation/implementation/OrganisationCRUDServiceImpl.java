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
    public Organisation getOrganisationByID(long id) {
        return organisationDB.getByID(id);
    }

    @Override
    public void deleteOrganisationByName(String name) {
        organisationDB.deleteByName(name);
    }

    @Override
    public void deleteOrganisationByID(long id) {
        organisationDB.deleteByID(id);
    }
}
