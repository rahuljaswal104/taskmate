package com.phsc.taskmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RoleDto;
import com.phsc.taskmate.service.RoleService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*")
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/save")
	public ResponseEntity<CustomResponse> roleSave(@RequestBody RoleDto roleDto){
		
		CustomResponse response = roleService.roleSave(roleDto);
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
	

}
