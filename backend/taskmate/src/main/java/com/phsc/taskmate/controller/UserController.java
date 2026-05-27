package com.phsc.taskmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<CustomResponse> register(@Valid @RequestBody RegisterUserDTO userDto) {

		CustomResponse response = userService.saveRegisterUser(userDto);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomResponse> login(@RequestBody RegisterUserDTO userDto) {
		CustomResponse response = userService.loginUser(userDto);
		return ResponseEntity.ok(response);
	}
	 
	@GetMapping("/employeecount")
    public ResponseEntity<CustomResponse> getEmployeeCount(){
		
		CustomResponse response= userService.employeeCount();
		return ResponseEntity.ok(response);
	}
}
