package com.phsc.taskmate.serviceimpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.EmployeeReportDTO;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.AssignTask;
import com.phsc.taskmate.entity.UserRegister;
import com.phsc.taskmate.enums.TaskStatus;
import com.phsc.taskmate.repository.AssignTaskRepository;
import com.phsc.taskmate.repository.UserRepository;
import com.phsc.taskmate.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private AssignTaskRepository assignTaskRepo;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<EmployeeReportDTO> getEmployeeReports(Long userId) {

		UserRegister user = userRepository.findById(userId).orElseThrow();

		List<EmployeeReportDTO> reports;

		String role = user.getRole().getRoleName();

		if ("SUPERADMIN".equals(role)) {
			reports = assignTaskRepo.getEmployeeReport();
		} else if ("DEPARTMENT ADMIN".equals(role)) {
			reports = assignTaskRepo.getDepartmentReport(user.getDepartment().getId());
		} else if ("MANAGER".equals(role)) {
			reports = assignTaskRepo.getManagerDepartmentReport(user.getDepartment().getId());
		} else {
			return Collections.emptyList();
		}

		for (EmployeeReportDTO dto : reports) {

			List<AssignTask> tasks = assignTaskRepo.findByEmployeeName(dto.getEmployeeName());

			dto.setPerformanceStatus(calculateStatus(tasks,dto.getCompletionPercentage()));
		}

		return reports;
	}
	
	private String calculateStatus(List<AssignTask> tasks,
            Double completionPercentage) {
				
				if (tasks == null || tasks.isEmpty()) {
				return "POOR";
				}
				
				if (completionPercentage == null) {
				return "POOR";
				}
				
				long overdueCount = tasks.stream()
				.filter(task ->
				(task.getTaskStatus() == TaskStatus.PENDING ||
				task.getTaskStatus() == TaskStatus.IN_PROGRESS)
				&& task.getTargetDate() != null
				&& LocalDate.now().isAfter(task.getTargetDate())
				)
				.count();
				
				// More than 50% overdue
				if (overdueCount > tasks.size() / 2) {
				return "POOR";
				}
				
				// Percentage Based
				if (completionPercentage >= 70) {
				return "EXCELLENT";
				}
				
				if (completionPercentage >= 30) {
				return "AVERAGE";
				}
				
				return "POOR";
				}

}
