package com.phsc.taskmate.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.TaskListDTO;
import com.phsc.taskmate.dto.UpdateTaskDto;
import com.phsc.taskmate.entity.AssignTask;

public interface AssignTaskService {

	CustomResponse save(AssignTask assignTask);

	CustomResponse getAllUser();

	List<TaskListDTO> getTaskByEmployee(String username);

	CustomResponse getTaskById(Long id);

	AssignTask updateTask(Long id, UpdateTaskDto request, MultipartFile file);

	CustomResponse getCountPendingAndCompleteTask();

	CustomResponse getTaskListByRoleAndDepartment(String role, Long id);

	CustomResponse getTaskList();
}
