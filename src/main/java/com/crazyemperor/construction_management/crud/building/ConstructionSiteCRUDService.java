package com.crazyemperor.construction_management.crud.building;

import com.crazyemperor.construction_management.entity.ConstructionSite;

import java.util.List;

public interface ConstructionSiteCRUDService {

    ConstructionSite add(ConstructionSite constructionSite);
    List<ConstructionSite> getAllConstructionSites();
    ConstructionSite getConstructionSiteByID(long id);
    ConstructionSite deleteConstructionSiteByName(String name, ConstructionSite update);
    ConstructionSite deleteConstructionSiteByID(long id, ConstructionSite update);

}
