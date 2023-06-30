package com.crazyemperor.construction_management.crud.building;

import com.crazyemperor.construction_management.entity.ConstructionSite;

import java.util.List;

public interface ConstructionSiteCRUDService {

    ConstructionSite add(ConstructionSite constructionSite);
    List<ConstructionSite> getAllConstructionSites();
    ConstructionSite getConstructionSiteByID(long id);
    void deleteConstructionSiteByName(String name);
    void deleteConstructionSiteByID(long id);

}
