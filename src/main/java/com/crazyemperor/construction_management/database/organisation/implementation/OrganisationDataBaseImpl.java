package com.crazyemperor.construction_management.database.organisation.implementation;

import com.crazyemperor.construction_management.database.organisation.OrganisationDataBaseService;
import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganisationDataBaseImpl implements OrganisationDataBaseService {

    private final OrganisationRepository organisationRepository;


    @Override
    @CacheEvict("Organisations")
    public Organisation addOrganisation(Organisation offer) {
        return organisationRepository.save(offer);
    }

    @Override
    @Cacheable("Organisations")
    public List<Organisation> getOrganisations() {
        return organisationRepository.findAll();
    }

    @Override
    @Cacheable("Organisations")
    public Organisation getByID(long id) {
        return organisationRepository.findById(id);
    }

    @Override
    @CacheEvict("Organisations")
    public Organisation deleteByName(String name, Organisation delete) {
        Optional<Organisation> organisationOptional = Optional.ofNullable(organisationRepository.findByName(name));
        if (organisationOptional.isPresent()) {
            Organisation organisation = organisationOptional.get();
            organisation.setDeleted(true);
            organisationRepository.save(organisation);
        }
        return organisationOptional.orElse(null);
    }

    @Override
    @CacheEvict("Organisations")
    public Organisation deleteByID(long id, Organisation delete) {
        Optional<Organisation> organisationOptional = Optional.ofNullable(organisationRepository.findById(id));
        if (organisationOptional.isPresent()) {
            Organisation organisation = organisationOptional.get();
            organisation.setDeleted(true);
            organisationRepository.save(organisation);
        }
        return organisationOptional.orElse(null);
    }
}
