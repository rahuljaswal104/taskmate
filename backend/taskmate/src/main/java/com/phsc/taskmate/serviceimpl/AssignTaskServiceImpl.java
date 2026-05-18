package com.phsc.taskmate.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.entity.AssignTask;
import com.phsc.taskmate.entity.UserRegister;
import com.phsc.taskmate.repository.AssignTaskRepository;
import com.phsc.taskmate.repository.UserRepository;
import com.phsc.taskmate.service.AssignTaskService;

@Service
public class AssignTaskServiceImpl implements AssignTaskService {

	@Autowired
	private AssignTaskRepository assignTaskRepo;

	@Autowired
	private UserRepository userRepo;

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
	public CustomResponse getTaskList() {
		
		 List<Object[]> taskList = assignTaskRepo.getTaskListData();

		    if(taskList.isEmpty()) {
		        return new CustomResponse("list empty", 400, null);
		    }

		    List<Map<String, Object>> response = new ArrayList<>();

		    for(Object[] obj : taskList) {

		        Map<String, Object> map = new HashMap<>();

		        map.put("title", obj[0]);
		        map.put("employeeName", obj[1]);
		        map.put("assignedBy", obj[2]);
		        map.put("assignedDate", obj[3]);
		        map.put("endDate", obj[4]);
		        map.put("taskStatus", obj[5]);
		        map.put("project", obj[6]);

		        response.add(map);
		    }

		    return new CustomResponse("Task List", 200, response);
	}
	
	
	
	

}
