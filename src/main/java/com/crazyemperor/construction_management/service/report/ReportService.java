package com.crazyemperor.construction_management.service.report;

import com.crazyemperor.construction_management.entity.Member;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <b>Retrieving </b> all department whose organisations participated in some building
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface ReportService {

    /**
     * Retrieving list of all department whose organisations participated in some building and number of participants
     * @param pageable - for paging
     * @return all department and number of participants
     * @throws DataNotFoundException
     *          thrown if no one organisation participated in some building.
     */
    Page<Member> getAllDepartmentsInBuildings(Pageable pageable);
}
