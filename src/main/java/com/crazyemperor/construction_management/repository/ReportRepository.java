package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ReportRepository extends PagingAndSortingRepository<Member, Long> {

    @Query(value = "SELECT org.department " +
            "FROM Member member " +
            "LEFT JOIN Organisation org ON member.id = org.id " +
            "GROUP BY org.department",
            countQuery = "SELECT COUNT(*) AS total_departments")
    Page<Member> countDepartment(Pageable pageable);
}
