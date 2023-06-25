package com.crazyemperor.construction_management.database.building;

import com.crazyemperor.construction_management.entity.ConstructionSite;

import java.util.List;

public interface ConstructionSiteDataBaseService {
    ConstructionSite addConstructionSite(ConstructionSite constructionSite);
    List<ConstructionSite> getConstructionSites();
    ConstructionSite getByID(long id);
    void deleteByName(String name);
    void deleteByID(long id);
}
