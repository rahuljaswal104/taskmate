package com.phsc.taskmate.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.dto.EmployeeReportDTO;
import com.phsc.taskmate.repository.AssignTaskRepository;
import com.phsc.taskmate.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired	
	private AssignTaskRepository assignTaskRepo;
	
	@Override
	public List<EmployeeReportDTO> getEmployeeReports() {
		 List<Object[]> rows = assignTaskRepo.getEmployeeReport();

	        List<EmployeeReportDTO> reports = new ArrayList<>();

	        for (Object[] row : rows) {

	            EmployeeReportDTO dto = new EmployeeReportDTO(
	                    (String) row[0],
	                    ((Number) row[1]).longValue(),
	                    ((Number) row[2]).longValue(),
	                    ((Number) row[3]).longValue(),
	                    ((Number) row[4]).longValue(),
	                    ((Number) row[5]).doubleValue());

	            reports.add(dto);
	        }

	        return reports;
	  
	}

}
