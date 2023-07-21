package com.crazyemperor.construction_management.database.building.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.database.building.ConstructionSiteDataBaseService;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConstructionSiteDataBaseImpl implements ConstructionSiteDataBaseService {

    private final ConstructionSiteRepository constructionSiteRepository;

    @Override
    @CacheEvict("construction_sites")
    public void addConstructionSite(ConstructionSite constructionSite) {
        constructionSiteRepository.save(constructionSite);
    }

    @Override
    @Cacheable("construction_sites")
    public List<ConstructionSite> getConstructionSites() {
        Optional<List<ConstructionSite>> constructionSites = Optional.of(constructionSiteRepository.findAll());

        return constructionSites
                .orElseThrow(() -> {
                       new NoDataFoundException("No one construction site found");
                    return null;
                });
    }

    @Override
    @Cacheable("construction_sites")
    public ConstructionSite getByID(long id) {
        return constructionSiteRepository.findById(id)
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No construction site found for id %d", id));
                    return null;
                });
    }

    @SneakyThrows
    @Override
    @CacheEvict("construction_sites")
    public void deleteByName(String name) {
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(name));

        if (constructionSiteOptional.isEmpty()) throw new NoDataFoundException(String.format("No construction site found for name %s", name));

        ConstructionSite constructionSite = constructionSiteOptional.get();

        if (constructionSite.isDeleted()) throw new IllegalArgumentException();
        constructionSite.setDeleted(true);
        constructionSiteRepository.save(constructionSite);
    }

    @Override
    @SneakyThrows
    @CacheEvict("construction_sites")
    public void deleteByID(long id) {
        ConstructionSite constructionSite = constructionSiteRepository.findById(id)
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No construction site found for id %d", id));
                    return null;
                });

        constructionSite.setDeleted(true);
        constructionSiteRepository.save(constructionSite);
    }

}
