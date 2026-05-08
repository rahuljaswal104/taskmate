package com.phsc.taskmate.service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.ProjectDto;
import com.phsc.taskmate.entity.Project;

public interface ProjectService {

	CustomResponse<Project> saveProject(ProjectDto projectDto);

	CustomResponse getProjectList();

}
