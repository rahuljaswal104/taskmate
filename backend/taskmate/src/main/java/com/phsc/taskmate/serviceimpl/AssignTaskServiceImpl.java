package com.phsc.taskmate.serviceimpl;

import java.util.List;

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

	@Override
	public CustomResponse save(AssignTask assignTask) {

		if (assignTask == null) {
			return new CustomResponse("please enter fields", 400, null);
		}

		AssignTask titlerepo = assignTaskRepo.findByTasktitle(assignTask.getTitle());
		if (titlerepo != null) {
			return new CustomResponse("This title already register", 409, assignTask);
		}
		assignTaskRepo.save(assignTask);
		return new CustomResponse("TaskAssign successfully ", 200, assignTask);
	}

	@Override
	public CustomResponse getAllUser() {

		
		List<UserRegister> userList = userRepo.getAllUserByStatus();
		if(userList.isEmpty()) {
			return new CustomResponse("list empty",400,null);
		}
		return new CustomResponse("Success ",200,userList); 
	}

}
