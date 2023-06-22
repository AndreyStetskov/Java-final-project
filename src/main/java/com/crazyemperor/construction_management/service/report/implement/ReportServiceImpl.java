package com.crazyemperor.construction_management.service.report.implement;

import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.repository.ReportRepository;
import com.crazyemperor.construction_management.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public Page<Member> getAllDepartmentsInBuildings(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "department", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "total_departments", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return reportRepository.countDepartment(pageable);
    }
}
