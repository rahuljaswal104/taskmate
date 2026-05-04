package com.phsc.taskmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.TaskMateRegisterUser;
import com.phsc.taskmate.service.TaskMateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaskMateController {

	@Autowired
	private TaskMateService mateService;

	@PostMapping("/register")
	public ResponseEntity<CustomResponse> register(@Valid @RequestBody RegisterUserDTO userDto) {

		CustomResponse response = mateService.saveRegisterUser(userDto);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomResponse> login(@RequestBody RegisterUserDTO userDto) {
		CustomResponse response = mateService.loginUser(userDto);
		return ResponseEntity.ok(response);
	}
}
