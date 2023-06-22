package com.crazyemperor.construction_management.service.report;

import com.crazyemperor.construction_management.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    Page<Member> getAllDepartmentsInBuildings(Pageable pageable);
}
