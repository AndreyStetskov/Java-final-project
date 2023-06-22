package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Organisation findByName(final String name);
    Organisation findById(final long id);
}
