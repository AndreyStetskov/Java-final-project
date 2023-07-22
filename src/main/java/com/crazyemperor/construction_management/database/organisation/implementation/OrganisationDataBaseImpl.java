package com.crazyemperor.construction_management.database.organisation.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.database.organisation.OrganisationDataBaseService;
import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.repository.OrganisationRepository;
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
    @CacheEvict("organisations")
    public Organisation addOrganisation(Organisation offer) {
        return organisationRepository.save(offer);
    }

    @SneakyThrows
    @Override
    @Cacheable("organisations")
    public List<Organisation> getOrganisations() {

        Optional<List<Organisation>> organisations = Optional.of(organisationRepository.findAll());

        return organisations
                .orElseThrow(() -> {
                    new NoDataFoundException("No one organisation found");
                    return null;
                });
    }

    @SneakyThrows
    @Override
    @Cacheable("organisations")
    public Organisation getByID(long id) {

        return organisationRepository.findById(id)
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No organisation found for id %d", id));
                    return null;
                });
    }

    @SneakyThrows
    @Override
    @CacheEvict("organisations")
    public void deleteByName(String name) {
        Optional<Organisation> organisationOptional = Optional.ofNullable(organisationRepository.findByName(name));
        if (organisationOptional.isPresent()) {
            Organisation organisation = organisationOptional.get();
            organisation.setDeleted(true);
            organisationRepository.save(organisation);
        }
        else try {
            throw new NoDataFoundException("This organisation didn't find");
        } catch (NoDataFoundException e) {
            throw new IllegalArgumentException("NoDataFoundException didn't work", e);
        }
    }

    @SneakyThrows
    @Override
    @CacheEvict("organisations")
    public void deleteByID(long id) {
        Organisation organisation = organisationRepository.findById(id)
                .orElseThrow(() -> {
                    try {
                        throw new NoDataFoundException(String.format("No organisation found for oid %d", id));
                    } catch (NoDataFoundException e) {
                        throw new IllegalArgumentException("NoDataFoundException didn't work", e);
                    }
                });

            organisation.setDeleted(true);
            organisationRepository.save(organisation);
    }
}
