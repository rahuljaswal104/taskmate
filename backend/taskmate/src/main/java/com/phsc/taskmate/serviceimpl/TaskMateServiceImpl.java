package com.phsc.taskmate.serviceimpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.TaskMateRegisterUser;
import com.phsc.taskmate.repository.TaskMateRepository;
import com.phsc.taskmate.service.TaskMateService;

@Service
public class TaskMateServiceImpl implements TaskMateService {

	@Autowired
	private TaskMateRepository mateRepository;

	@Override
	public String saveRegisterUser(RegisterUserDTO userDto) {

		try {

			if (userDto == null) {
				return "user is null";
			}

			List<TaskMateRegisterUser> userList = mateRepository.findAll();
			List<RegisterUserDTO> us = mateRepository.findByUsername(userDto.getUsername());

			if (!us.isEmpty()) {
				return "Username already exists";
			}

			if (userDto.getRole().contains("SUPERADMIN")) {
				for (TaskMateRegisterUser u : userList) {
					if (u.getRole().contains("SUPERADMIN")) {
						return "Superadmin already exists";
					}
				}
			}

			TaskMateRegisterUser newUSer = new TaskMateRegisterUser();

			newUSer.setName(userDto.getName());
			newUSer.setUsername(userDto.getUsername());
			newUSer.setRole(userDto.getRole());
			newUSer.setGender(userDto.getGender());
			newUSer.setStatus("ACTIVE");

			String hashed = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
			newUSer.setPassword(hashed);

			mateRepository.save(newUSer);
			return "User registered successfully";

		} catch (Exception e) {
			e.printStackTrace();
			return "Somthing went wrong";
		}
	}
	
	@Override
	public String loginUser(RegisterUserDTO userDto) {
		
		RegisterUserDTO us = mateRepository.findByUser(userDto.getUsername());
		
		
		if(us==null) {
		return "user not registered yet";	
		}
		
		
		if (userDto.getPassword() == null) {
	        return "Password not set";
	    }
		
		
		if (!BCrypt.checkpw(userDto.getPassword(), us.getPassword())) {
            return "Invalid password";
        }
		
		return "login success";
	}

}
