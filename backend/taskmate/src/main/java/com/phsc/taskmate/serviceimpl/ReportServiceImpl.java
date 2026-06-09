package com.phsc.taskmate.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.EmployeeReportDTO;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.UserRegister;
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

		UserRegister user = userRepository.findById(userId).orElse(null);

		String role = user.getRole().getRoleName();

		if ("SUPERADMIN".equals(role)) {
			return assignTaskRepo.getEmployeeReport();
		}

		if ("DEPARTMENT ADMIN".equals(role)) {
			return assignTaskRepo.getDepartmentReport(user.getDepartment().getId());
		}

		if ("MANAGER".equals(role)) {
			return assignTaskRepo.getManagerDepartmentReport(user.getDepartment().getId());
		}

		return Collections.emptyList();
	}

}
