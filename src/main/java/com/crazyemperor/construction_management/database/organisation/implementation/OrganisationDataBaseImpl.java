package com.crazyemperor.construction_management.database.organisation.implementation;

import com.crazyemperor.construction_management.database.organisation.OrganisationDataBaseService;
import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.repository.OrganisationRepository;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    @Cacheable("Organisations")
    public List<Organisation> getOrganisations() {

        Optional<List<Organisation>> organisations = Optional.of(organisationRepository.findAll());

        return organisations
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @Cacheable("Organisations")
    public Organisation getByID(long id) {

        return organisationRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @CacheEvict("Organisations")
    public void deleteByName(String name) {
        Optional<Organisation> organisationOptional = Optional.ofNullable(organisationRepository.findByName(name));
        if (organisationOptional.isPresent()) {
            Organisation organisation = organisationOptional.get();
            organisation.setDeleted(true);
            organisationRepository.save(organisation);
        }
        else throw new DataNotFoundException();
    }

    @SneakyThrows
    @Override
    @CacheEvict("Organisations")
    public void deleteByID(long id) {
        Organisation organisation = organisationRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);

            organisation.setDeleted(true);
            organisationRepository.save(organisation);
    }
}
