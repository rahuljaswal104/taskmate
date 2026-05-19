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
		
		return assignTaskRepo.getEmployeeReport();
	}

}
