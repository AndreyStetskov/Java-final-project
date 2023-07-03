package com.crazyemperor.construction_management.service.building.implement;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberType;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import com.crazyemperor.construction_management.repository.OfferRepository;
import com.crazyemperor.construction_management.service.building.ConstructionSiteService;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        buildingList.stream()
                .filter(current -> current.getStatus().equals(ConstructionSiteStatus.ACTIVE))
                .collect(Collectors.toList());

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
                .collect(Collectors.toList());

        for (ConstructionSite building : buildingList) {
            BigDecimal result = sum.add(building.getAmount());
            sum = result;
        }

        return sum;
    }

    @SneakyThrows
    @Override
    public ConstructionSite selectedConstructor(ConstructionSite constructor, Offer selected) {
        Optional<Offer> offerOptional = Optional.ofNullable(offerRepository.findWithMembersByTitle(selected.getTitle()));
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(constructor.getTitle()));

        if (offerOptional.isPresent() && constructionSiteOptional.isPresent()) {
            if (!constructor.getConstructor().getStatus().equals(MemberStatus.ACTIVE)) {
                throw new IllegalArgumentException("Status of member must be active");
            }
            else constructor.getConstructor().getType().add(MemberType.CONSTRUCTOR);

            constructor.setConstructor(selected.getAcceptor());
            constructionSiteRepository.save(constructor);
        }
        else throw new DataNotFoundException();

        return constructor;
    }

    @Override
    public ConstructionSite selectedEngineering(ConstructionSite engineering, Offer offer) {
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
        return engineering;
    }

    @Override
    public ConstructionSite selectedProtector(ConstructionSite projector, Offer offer) {
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
        return projector;
    }


}
