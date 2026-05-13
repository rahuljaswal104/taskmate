package com.phsc.taskmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.dto.EmployeeReportDTO;
import com.phsc.taskmate.service.ReportService;


@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {

	@Autowired
    private ReportService reportService;

    @GetMapping("/employee")
    public List<EmployeeReportDTO> getEmployeeReport() {

        return reportService.getEmployeeReports();
    }
	
}
