package com.crazyemperor.construction_management.service.building;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Offer;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ConstructionSiteService {

    BigDecimal getSumAllBuildings();
    BigDecimal getSumCurrentBuildings();
    BigDecimal getSumBuildingsAfterDate(LocalDate date);

    ConstructionSite selectedConstructor(ConstructionSite constructor, Offer bestProposal);
    ConstructionSite selectedEngineering(ConstructionSite engineering, Offer bestProposal);
    ConstructionSite selectedProtector(ConstructionSite protector, Offer bestProposal);
}
