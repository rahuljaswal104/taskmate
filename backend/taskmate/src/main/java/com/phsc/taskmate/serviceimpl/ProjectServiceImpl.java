package com.phsc.taskmate.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.ProjectDto;
import com.phsc.taskmate.entity.Department;
import com.phsc.taskmate.entity.Project;
import com.phsc.taskmate.repository.DepartmentRepository;
import com.phsc.taskmate.repository.ProjectRepository;
import com.phsc.taskmate.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public CustomResponse<Project> saveProject(ProjectDto projectDto) {

		if (projectDto == null) {
			return new CustomResponse<>("Project data null", 400, null);
		}

		Project projectPresent = projectRepository.findByProjectName(projectDto.getProjectName());

		List<Department> departments = departmentRepository.findAllById(projectDto.getDepartmentIds());

		if (projectPresent != null) {
			return new CustomResponse<>("already exist", 409, null);
		}

		if (departments.size() != projectDto.getDepartmentIds().size()) {

			return new CustomResponse<>("departments not found", 404, null);
		}

		Project project = new Project();

		project.setProjectName(projectDto.getProjectName());
		project.setDepartments(departments);
		projectRepository.save(project);

		return new CustomResponse<>("Project saved successfully", 200, project);
	}

}
