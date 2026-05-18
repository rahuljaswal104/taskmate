package com.phsc.taskmate.service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.entity.AssignTask;

public interface AssignTaskService {

	CustomResponse save(AssignTask assignTask);
	
	CustomResponse getAllUser();
}
