package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @GetMapping(value = "/member/all-departments/count")
    public ResponseEntity<Page<Member>> countAllDepartment(@RequestBody Pageable pageable) {
        Page<Member> count = reportService.getAllDepartmentsInBuildings(pageable);

        return count != null ? ResponseEntity.ok(count) : ResponseEntity.noContent().build();
    }


}
