package com.crazyemperor.construction_management.service.building.implement;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberType;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import com.crazyemperor.construction_management.repository.OfferRepository;
import com.crazyemperor.construction_management.service.building.ConstructionSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConstructionSiteServiceImpl implements ConstructionSiteService {

    private final ConstructionSiteRepository constructionSiteRepository;
    private final OfferRepository offerRepository;


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

        List<ConstructionSite> filterBuilding = buildingList.stream()
                .filter(current -> current.getStatus().equals(ConstructionSiteStatus.ACTIVE))
                .toList();

        for (ConstructionSite building : filterBuilding) {
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

        List<ConstructionSite> filterBuilding = buildingList.stream()
                .filter(current -> current.getStart().isAfter(date))
                .toList();

        for (ConstructionSite building : filterBuilding) {
            BigDecimal result = sum.add(building.getAmount());
            sum = result;
        }

        return sum;
    }

    @Override
    public void selectedConstructor(ConstructionSite constructor, Offer selected) {
        Optional<Offer> offerOptional = Optional.ofNullable(offerRepository.findWithMembersByTitle(selected.getTitle()));
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(constructor.getTitle()));

        if (offerOptional.isPresent() && constructionSiteOptional.isPresent()) throw new IllegalArgumentException("Status of member must be active");

        if (!constructor.getConstructor().getStatus().equals(MemberStatus.ACTIVE)) {
            throw new IllegalArgumentException("Status of member must be active");
        }
        else constructor.getConstructor().getType().add(MemberType.CONSTRUCTOR);

        constructor.setConstructor(selected.getAcceptor());
        constructionSiteRepository.save(constructor);
    }

    @Override
    public void selectedEngineering(ConstructionSite engineering, Offer offer) {
        Optional<Offer> offerOptional = Optional.ofNullable(offerRepository.findWithMembersByTitle(offer.getTitle()));
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(engineering.getTitle()));

        if (offerOptional.isPresent() && constructionSiteOptional.isPresent()) {
            if (!engineering.getConstructor().getStatus().equals(MemberStatus.ACTIVE)) {
                throw new IllegalArgumentException("Status of member must be active");
            }
            else engineering.getConstructor().getType().add(MemberType.ENGINEERING);

            engineering.setEngineering(offer.getAcceptor());
            constructionSiteRepository.save(engineering);
        }
        else try {
            throw new NoDataFoundException("This offer didn't find");
        } catch (NoDataFoundException e) {
            throw new IllegalArgumentException("NoDataFoundException didn't work", e);
        }
    }

    @Override
    public void selectedProjector(ConstructionSite projector, Offer offer) {
        Optional<Offer> offerOptional = Optional.ofNullable(offerRepository.findWithMembersByTitle(offer.getTitle()));
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(projector.getTitle()));

        if (offerOptional.isPresent() && constructionSiteOptional.isPresent()) {
            if (!projector.getConstructor().getStatus().equals(MemberStatus.ACTIVE)) {
                throw new IllegalArgumentException("Status of member must be active");
            }
            else projector.getConstructor().getType().add(MemberType.PROJECTOR);

            projector.setProjector(offer.getAcceptor());
            constructionSiteRepository.save(projector);
        }
        else try {
            throw new NoDataFoundException("This offer didn't find");
        } catch (NoDataFoundException e) {
            throw new RuntimeException("NoDataFoundException didn't work", e);
        }
    }
}
