package com.phsc.taskmate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.entity.Department;
import com.phsc.taskmate.repository.DepartmentRepository;
import com.phsc.taskmate.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("http://localhost:5173")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
//	@Autowired
//	private DepartmentRepository departmentRepo;
	
	@GetMapping("/get")
	public List<Department> getAll(){
		
		return departmentService.getAll();
	}

	@PostMapping("/save")
	public ResponseEntity<CustomResponse> save(@RequestBody Department dpart) {
		CustomResponse response = departmentService.save(dpart);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/getDepartmentCount")
	public ResponseEntity<CustomResponse> getTotalDepartmentCount(){
		
		CustomResponse response = departmentService.getTotalDepartmentCount();
		
		return ResponseEntity.ok(response);
	}


	
	
}
