package com.crazyemperor.construction_management.database.building.implement;

import com.crazyemperor.construction_management.database.building.ConstructionSiteDataBaseService;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import lombok.RequiredArgsConstructor;
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
    @CacheEvict("ConstructionSites")
    public ConstructionSite addConstructionSite(ConstructionSite constructionSite) {
        return constructionSiteRepository.save(constructionSite);
    }

    @Override
    @Cacheable("ConstructionSites")
    public List<ConstructionSite> getConstructionSites() {
        return constructionSiteRepository.findAll();
    }

    @Override
    @Cacheable("ConstructionSites")
    public ConstructionSite getByID(long id) {
        return constructionSiteRepository.findById(id);
    }

    @Override
    @CacheEvict("ConstructionSites")
    public ConstructionSite deleteByName(String name, ConstructionSite update) {
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findByTitle(name));
        if (constructionSiteOptional.isPresent()) {
            ConstructionSite constructionSite = constructionSiteOptional.get();
            constructionSite.setDeleted(true);
            constructionSiteRepository.save(constructionSite);
        }
        return constructionSiteOptional.orElse(null);
    }

    @Override
    @CacheEvict("ConstructionSites")
    public ConstructionSite deleteByID(long id, ConstructionSite update) {
        Optional<ConstructionSite> constructionSiteOptional = Optional.ofNullable(constructionSiteRepository.findById(id));
        if (constructionSiteOptional.isPresent()) {
            ConstructionSite constructionSite = constructionSiteOptional.get();
            constructionSite.setDeleted(true);
            constructionSiteRepository.save(constructionSite);
        }
        return constructionSiteOptional.orElse(null);
    }

}
