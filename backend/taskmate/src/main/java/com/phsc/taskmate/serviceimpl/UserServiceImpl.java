package com.phsc.taskmate.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.Department;
import com.phsc.taskmate.entity.Role;
import com.phsc.taskmate.entity.UserRegister;
import com.phsc.taskmate.repository.DepartmentRepository;
import com.phsc.taskmate.repository.RoleRepository;
import com.phsc.taskmate.repository.UserRepository;
import com.phsc.taskmate.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public CustomResponse saveRegisterUser(RegisterUserDTO userDto) {

		try {

			if (userDto == null) {
				return new CustomResponse("userdata missing", 400, userDto);
			}

			List<UserRegister> userList = userRepository.findAll();
			List<RegisterUserDTO> us = userRepository.findByUsername(userDto.getUsername());

			if (!us.isEmpty()) {
				return new CustomResponse("userdata missing", 400, userDto);
			}

			if (userDto.getRole().equals("SUPERADMIN")) {
				for (UserRegister u : userList) {
					if (u.getRole().equals("SUPERADMIN")) {
						return new CustomResponse("superadmin already exist", 409, userDto.getUsername());
					}
				}
			}

			UserRegister newUser = new UserRegister();

			newUser.setName(userDto.getName());
			newUser.setUsername(userDto.getUsername());
			
			Role role = roleRepository.findById(userDto.getRole().getId())
			        .orElseThrow(() -> new RuntimeException("Role not found"));
			newUser.setRole(role);
			
			newUser.setGender(userDto.getGender());
			newUser.setPhone(userDto.getPhone());
			
			Department department = departmentRepository.findById(userDto.getDepartment().getId())
			        .orElseThrow(() -> new RuntimeException("Department not found"));
			newUser.setDepartment(department);
			
			newUser.setDesignation(userDto.getDesignation());
			newUser.setStatus("ACTIVE");
			String encPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
			newUser.setPassword(encPassword);
			userRepository.save(newUser);
			return new CustomResponse("user data saved successfully", 200, userDto);

		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse("Somthing went wrong", 500, userDto);
		}
	}

	@Override
	public CustomResponse loginUser(RegisterUserDTO userDto) {

		RegisterUserDTO us = userRepository.findByUser(userDto.getUsername());

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
		 userDto.setDepartment(us.getDepartment());
		 userDto.setRole(us.getRole());
         userDto.setPassword(null);
		return new CustomResponse("Login successful", 200, userDto);

	}

	@Override
	public CustomResponse employeeCount() {
		long employeecount = userRepository.count();
		
		return new CustomResponse("employee count", 200, employeecount);
	}

}
