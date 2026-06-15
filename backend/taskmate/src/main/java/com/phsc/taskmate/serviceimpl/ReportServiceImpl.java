package com.phsc.taskmate.serviceimpl;

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

			dto.setPerformanceStatus(calculateStatus(tasks));
		}

		return reports;
	}

	private String calculateStatus(List<AssignTask> tasks) {

		if (tasks == null || tasks.isEmpty()) {
			return "POOR";
		}

		boolean averageFound = false;
		boolean excellentFound = false;

		for (AssignTask task : tasks) {

			// Null Safety
			if (task == null || task.getTaskStatus() == null || task.getTargetDate() == null
					|| task.getEndDate() == null) {

				return "POOR";
			}

			// COMPLETED + targetDate >= endDate
			if (task.getTaskStatus() == TaskStatus.COMPLETED && (task.getTargetDate().isEqual(task.getEndDate())
					|| task.getTargetDate().isAfter(task.getEndDate()))) {

				excellentFound = true;
			}

			// PENDING or IN_PROGRESS + targetDate < endDate
			if ((task.getTaskStatus() == TaskStatus.PENDING || task.getTaskStatus() == TaskStatus.IN_PROGRESS)
					&& task.getTargetDate().isBefore(task.getEndDate())) {

				return "POOR";
			}

			// IN_PROGRESS + targetDate > endDate
			if (task.getTaskStatus() == TaskStatus.IN_PROGRESS && task.getTargetDate().isAfter(task.getEndDate())) {

				averageFound = true;
			}
		}

		if (excellentFound) {
			return "EXCELLENT";
		}

		if (averageFound) {
			return "AVERAGE";
		}

		return "POOR";
	}

}
