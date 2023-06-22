package com.crazyemperor.construction_management.service.building.implement;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import com.crazyemperor.construction_management.service.building.ConstructionSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionSiteServiceImpl implements ConstructionSiteService {

    private final ConstructionSiteRepository constructionSiteRepository;


    @Override
    public BigDecimal getSumAllBuildings() {

        List<ConstructionSite> buildingList = constructionSiteRepository.findAll();

        BigDecimal sum = BigDecimal.ZERO;

        if (buildingList.isEmpty()) return sum;

        for (ConstructionSite building : buildingList) {
            BigDecimal result = sum.add(building.getAmount());
            sum = result;
        }

        return sum;
    }

    @Override
    public BigDecimal getSumCurrentBuildings() {
        List<ConstructionSite> buildingList = constructionSiteRepository.findAll();

        BigDecimal sum = BigDecimal.ZERO;

        if (buildingList.isEmpty()) return sum;

        buildingList.stream()
                .filter(current -> current.getStatus().equals(ConstructionSiteStatus.ACTIVE))
                .toList();

        for (ConstructionSite building : buildingList) {
            BigDecimal result = sum.add(building.getAmount());
            sum = result;
        }

        return sum;
    }

    @Override
    public BigDecimal getSumBuildingsAfterDate(LocalDate date) {
        List<ConstructionSite> buildingList = constructionSiteRepository.findAll();

        BigDecimal sum = BigDecimal.ZERO;

        if (buildingList.isEmpty()) return sum;

        buildingList.stream()
                .filter(current -> current.getStart().isAfter(date))
                .toList();

        for (ConstructionSite building : buildingList) {
            BigDecimal result = sum.add(building.getAmount());
            sum = result;
        }

        return sum;
    }
}
