package com.phsc.taskmate.service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RegisterUserDTO;

public interface UserService {

	public CustomResponse saveRegisterUser(RegisterUserDTO user);
	
	public CustomResponse loginUser(RegisterUserDTO user);
}
