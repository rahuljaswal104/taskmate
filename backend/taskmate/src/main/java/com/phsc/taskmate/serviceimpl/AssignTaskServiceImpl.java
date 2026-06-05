package com.phsc.taskmate.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.TaskListDTO;
import com.phsc.taskmate.dto.UpdateTaskDto;
import com.phsc.taskmate.entity.AssignTask;
import com.phsc.taskmate.entity.Department;
import com.phsc.taskmate.entity.UserRegister;
import com.phsc.taskmate.enums.TaskStatus;
import com.phsc.taskmate.repository.AssignTaskRepository;
import com.phsc.taskmate.repository.DepartmentRepository;
import com.phsc.taskmate.repository.UserRepository;
import com.phsc.taskmate.service.AssignTaskService;

@Service
public class AssignTaskServiceImpl implements AssignTaskService {

	@Autowired
	private AssignTaskRepository assignTaskRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DepartmentRepository departmentRepository;

//	@Override
//	public CustomResponse save(AssignTask assignTask) {
//
//		if (assignTask == null) {
//			return new CustomResponse("please enter fields", 400, null);
//		}
//
//		AssignTask titlerepo = assignTaskRepo.findByTasktitle(assignTask.getTitle());
//		if (titlerepo != null) {
//			return new CustomResponse("This title already register", 409, assignTask);
//		}
//		assignTaskRepo.save(assignTask);
//		return new CustomResponse("TaskAssign successfully ", 200, assignTask);
//	}

	public CustomResponse save(AssignTask assignTask) {

		if (assignTask == null) {
			return new CustomResponse("please enter fields", 400, null);
		}

		AssignTask titlerepo = assignTaskRepo.findByTasktitle(assignTask.getTitle());
		if (titlerepo != null) {
			return new CustomResponse("This title already register", 409, assignTask);
		}

		List<UserRegister> employeeList = new ArrayList<>();

		if (assignTask.getEmployees() != null) {

			for (UserRegister emp : assignTask.getEmployees()) {

				UserRegister employee = userRepo.findById(emp.getId())
						.orElseThrow(() -> new RuntimeException("Employee Not Found : " + emp.getId()));

				employeeList.add(employee);

			}
		}

		assignTask.setEmployees(employeeList);
		assignTaskRepo.save(assignTask);
		return new CustomResponse("TaskAssign successfully ", 200, assignTask);

	}

	@Override
	public CustomResponse getAllUser() {

		List<UserRegister> userList = userRepo.getAllUserByStatus();
		if (userList.isEmpty()) {
			return new CustomResponse("list empty", 400, null);
		}
		return new CustomResponse("Success ", 200, userList);
	}

	
	
	@Override
	public CustomResponse getTaskListByRoleAndDepartment(String role, Long id) {
		
			Department department = departmentRepository.findById(id).orElse(null);
	
		    if (department == null) {
		        return new CustomResponse("Department not found", 404, null);
		    }
		    
			
			List<TaskListDTO> departmentTaskList = assignTaskRepo.getTaskListByRoleAndDepartment(role,id);
			
			
		    
			if (departmentTaskList.isEmpty()){
				return new CustomResponse("list empty", 400, null);
			}
			
			
			return new CustomResponse("Task List", 200, departmentTaskList);
		
	}
	
	
	
	

	@Override
	public List<TaskListDTO> getTaskByEmployee(String username) {

		List<TaskListDTO> usertasks = assignTaskRepo.getTaskByUserName(username);

		return usertasks;
	}

	
	
	@Override
	public CustomResponse getTaskById(Long id) {
		Optional<AssignTask> taskdtl = assignTaskRepo.findById(id);

		if (taskdtl.isEmpty()) {
			return new CustomResponse("task detail not get", 400, null);
		}

		return new CustomResponse("success", 200, taskdtl);
	}
	
	
	

	@Override
	public AssignTask updateTask(Long id, UpdateTaskDto request, MultipartFile file) {

		AssignTask task = assignTaskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));

		// ===============================
		// UPDATE REQUIRED FIELDS
		// ===============================

		task.setTaskStatus(request.getTaskStatus());

		task.setStartDate(request.getStartDate());

		task.setEndDate(request.getEndDate());

		task.setRemarks(request.getRemarks());

		// ===============================
		// FILE VALIDATION + UPLOAD
		// ===============================

		if (file != null && !file.isEmpty()) {

			String contentType = file.getContentType();

			// Allowed File Types

			if (

			!contentType.equals("application/pdf")

					&& !contentType.equals("image/png")

					&& !contentType.equals("image/jpeg")

					&& !contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")

					&& !contentType.equals("application/vnd.ms-excel")

					&& !contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

			) {

				throw new RuntimeException("Only PDF, JPG, PNG, DOCX and Excel files are allowed");
			}

			// Max File Size = 5MB

			if (file.getSize() > 5 * 1024 * 1024) {

				throw new RuntimeException("File size must be less than 5MB");
			}

			try {

				String uploadDir = "uploads/";

				File dir = new File(uploadDir);

				if (!dir.exists()) {

					dir.mkdirs();

				}

				String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

				String filePath = uploadDir + fileName;

				Files.copy(file.getInputStream(), Paths.get(filePath));

				// Save File Details

				task.setFileName(fileName);

				task.setFileType(contentType);

				task.setFilePath(filePath);

			} catch (IOException e) {

				throw new RuntimeException("File Upload Failed");
			}
		}

		return assignTaskRepo.save(task);
	}

	@Override
	public CustomResponse getCountPendingAndCompleteTask() {

	    Long pendingCount =
	    		assignTaskRepo.countByTaskStatus(TaskStatus.PENDING);

	    Long completedCount =
	    		assignTaskRepo.countByTaskStatus(TaskStatus.COMPLETED);

	    Map<String, Long> map = new HashMap<>();

	    map.put("pendingCount", pendingCount);

	    map.put("completedCount", completedCount);
		
		return new CustomResponse("Success", 200, map);
	}

	@Override
	public CustomResponse getTaskList() {
			List<TaskListDTO> taskList = assignTaskRepo.getTaskListData();
			

			if (taskList.isEmpty()){
				return new CustomResponse("list empty", 400, null);
			}
			
			return new CustomResponse("Task List", 200, taskList);
			
		}



}
