package com.crazyemperor.construction_management.service.building;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ConstructionSiteService {

    BigDecimal getSumAllBuildings();
    BigDecimal getSumCurrentBuildings();
    BigDecimal getSumBuildingsAfterDate(LocalDate date);
}
