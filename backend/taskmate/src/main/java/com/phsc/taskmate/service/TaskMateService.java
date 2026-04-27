package com.phsc.taskmate.service;

import com.phsc.taskmate.dto.RegisterUserDTO;

public interface TaskMateService {

	public String saveRegisterUser(RegisterUserDTO user);
	
	public String loginUser(RegisterUserDTO user);
}
