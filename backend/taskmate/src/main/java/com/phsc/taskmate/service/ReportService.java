package com.phsc.taskmate.service;

import java.util.List;

import com.phsc.taskmate.dto.EmployeeReportDTO;

public interface ReportService {

	List<EmployeeReportDTO> getEmployeeReports(Long userId);

}
