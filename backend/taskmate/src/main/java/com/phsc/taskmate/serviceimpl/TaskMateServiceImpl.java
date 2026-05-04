package com.phsc.taskmate.serviceimpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.TaskMateRegisterUser;
import com.phsc.taskmate.repository.TaskMateRepository;
import com.phsc.taskmate.service.TaskMateService;

@Service
public class TaskMateServiceImpl implements TaskMateService {

	@Autowired
	private TaskMateRepository mateRepository;

	@Override
	public CustomResponse saveRegisterUser(RegisterUserDTO userDto) {

		try {

			if (userDto == null) {
				return new CustomResponse("userdata missing", 400, userDto);
			}

			List<TaskMateRegisterUser> userList = mateRepository.findAll();
			List<RegisterUserDTO> us = mateRepository.findByUsername(userDto.getUsername());

			if (!us.isEmpty()) {
				return new CustomResponse("user data missing", 400, userDto);
			}

			if (userDto.getRole().contains("SUPERADMIN")) {
				for (TaskMateRegisterUser u : userList) {
					if (u.getRole().contains("SUPERADMIN")) {
						return new CustomResponse("superadmin already exist", 409, userDto.getUsername());
					}
				}
			}

			TaskMateRegisterUser newUSer = new TaskMateRegisterUser();

			newUSer.setName(userDto.getName());
			newUSer.setUsername(userDto.getUsername());
			newUSer.setRole(userDto.getRole());
			newUSer.setGender(userDto.getGender());
			newUSer.setStatus("ACTIVE");

			String encPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
			newUSer.setPassword(encPassword);

			mateRepository.save(newUSer);
			return new CustomResponse("user saved successfully", 200, userDto);

		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse("Somthing went wrong", 500, userDto);
		}
	}

	@Override
	public CustomResponse loginUser(RegisterUserDTO userDto) {

		RegisterUserDTO us = mateRepository.findByUser(userDto.getUsername());

		if (us == null) {
			return new CustomResponse("No user found", 404, null);
		}

		if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
			return new CustomResponse("Password cannot be null or empty", 400, null);
		}

		boolean isPasswordMatch = BCrypt.checkpw(userDto.getPassword(), us.getPassword());

		if (!isPasswordMatch) {
			return new CustomResponse("Incorrect password", 401, null);
		}

		return new CustomResponse("Login successful", 200, userDto);

	}

}
