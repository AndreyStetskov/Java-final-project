package com.crazyemperor.construction_management.crud.building.implementation;

import com.crazyemperor.construction_management.crud.building.ConstructionSiteCRUDService;
import com.crazyemperor.construction_management.database.building.ConstructionSiteDataBaseService;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionSiteCRUDServiceImpl implements ConstructionSiteCRUDService {

    private final ConstructionSiteDataBaseService constructionSiteDB;


    @Override
    public void add(ConstructionSite constructionSite) {
        constructionSiteDB.addConstructionSite(constructionSite);
    }

    @Override
    public List<ConstructionSite> getAllConstructionSites() {
        return constructionSiteDB.getConstructionSites();
    }

    @Override
    public ConstructionSite getConstructionSiteByID(long id) {
        return constructionSiteDB.getByID(id);
    }

    @Override
    public void deleteConstructionSiteByName(String name) {
       constructionSiteDB.deleteByName(name);
    }

    @Override
    public void deleteConstructionSiteByID(long id) {
       constructionSiteDB.deleteByID(id);
    }
}
