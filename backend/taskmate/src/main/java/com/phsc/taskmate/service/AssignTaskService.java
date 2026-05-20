package com.phsc.taskmate.service;

import java.util.List;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.TaskListDTO;
import com.phsc.taskmate.entity.AssignTask;

public interface AssignTaskService {

	CustomResponse save(AssignTask assignTask);
	
	CustomResponse getAllUser();

	CustomResponse getTaskList();

	List<TaskListDTO> getTaskByEmployee(String username);

	CustomResponse getTaskById(Long id);
}
