package com.phsc.taskmate.serviceimpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.UserRegister;
import com.phsc.taskmate.repository.UserRepository;
import com.phsc.taskmate.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository mateRepository;

	@Override
	public CustomResponse saveRegisterUser(RegisterUserDTO userDto) {

		try {

			if (userDto == null) {
				return new CustomResponse("userdata missing", 400, userDto);
			}

			List<UserRegister> userList = mateRepository.findAll();
			List<RegisterUserDTO> us = mateRepository.findByUsername(userDto.getUsername());

			if (!us.isEmpty()) {
				return new CustomResponse("userdata missing", 400, userDto);
			}

			if (userDto.getRole().contains("SUPERADMIN")) {
				for (UserRegister u : userList) {
					if (u.getRole().contains("SUPERADMIN")) {
						return new CustomResponse("superadmin already exist", 409, userDto.getUsername());
					}
				}
			}

			UserRegister newUser = new UserRegister();

			newUser.setName(userDto.getName());
			newUser.setUsername(userDto.getUsername());
			newUser.setRole(userDto.getRole());
			newUser.setGender(userDto.getGender());
			newUser.setPhone(userDto.getPhone());
			newUser.setDepartment(userDto.getDepartment());
			newUser.setDesignation(userDto.getDesignation());
			newUser.setStatus("ACTIVE");
			String encPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
			newUser.setPassword(encPassword);
			mateRepository.save(newUser);
			return new CustomResponse("user data saved successfully", 200, userDto);

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
		
		 userDto.setName(us.getName());
		 userDto.setUsername(us.getUsername());
		 userDto.setRole(us.getRole());
         userDto.setPassword(null);
		return new CustomResponse("Login successful", 200, userDto);

	}

}
