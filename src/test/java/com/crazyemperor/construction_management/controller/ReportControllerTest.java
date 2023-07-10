package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.service.report.ReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    private Page page;
    private Pageable pageable;


    @Test
    void countAllDepartment_ReturnReport_Success() {

//        given
        ResponseEntity<Page<Member>> expected = new ResponseEntity<>(page, HttpStatus.NO_CONTENT);

        when(reportService.getAllDepartmentsInBuildings(pageable)).thenReturn(expected.getBody());

//        when
        ResponseEntity<Page<Member>> actual = reportController.countAllDepartment(pageable);

//        then
        assertEquals(expected, actual);
        verify(reportService, times(1)).getAllDepartmentsInBuildings(pageable);
    }
}