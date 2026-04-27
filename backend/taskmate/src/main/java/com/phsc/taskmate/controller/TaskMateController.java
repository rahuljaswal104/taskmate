package com.phsc.taskmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.TaskMateRegisterUser;
import com.phsc.taskmate.service.TaskMateService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaskMateController {

	@Autowired
	private TaskMateService mateService;

	@PostMapping("/register")
	public String register(@RequestBody RegisterUserDTO userDto) {

		return mateService.saveRegisterUser(userDto);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody RegisterUserDTO userDto) {
		
		return mateService.loginUser(userDto);
	}
}
