package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConstructionSiteRepository extends JpaRepository<ConstructionSite, Long> {

    ConstructionSite findByTitle(final String name);
    ConstructionSite findById(final long id);

}
