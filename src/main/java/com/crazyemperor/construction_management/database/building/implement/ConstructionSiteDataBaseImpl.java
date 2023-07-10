package com.crazyemperor.construction_management.database.building.implement;

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

    @SneakyThrows
    @Override
    @Cacheable("construction_sites")
    public List<ConstructionSite> getConstructionSites() {
        Optional<List<ConstructionSite>> constructionSites = Optional.of(constructionSiteRepository.findAll());

        return constructionSites
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @Cacheable("construction_sites")
    public ConstructionSite getByID(long id) {
        return constructionSiteRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @CacheEvict("construction_sites")
    public void deleteByName(String name) {
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(name));

        if (constructionSiteOptional.isPresent()) throw new DataNotFoundException();

        ConstructionSite constructionSite = constructionSiteOptional.get();

        if (constructionSite.isDeleted()) throw new IllegalArgumentException(); // ANOTHER EXCEPTION

        constructionSite.setDeleted(true);
        constructionSiteRepository.save(constructionSite);
    }

    @Override
    @SneakyThrows
    @CacheEvict("construction_sites")
    public void deleteByID(long id) {
        ConstructionSite constructionSite = constructionSiteRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);

        constructionSite.setDeleted(true);
        constructionSiteRepository.save(constructionSite);
    }

}
