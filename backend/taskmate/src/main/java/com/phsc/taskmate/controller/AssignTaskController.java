package com.phsc.taskmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.TaskListDTO;
import com.phsc.taskmate.entity.AssignTask;
import com.phsc.taskmate.service.AssignTaskService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/assgintask")
public class AssignTaskController {
	
	@Autowired
	private AssignTaskService assignTaskService;

	
	@PostMapping("/save")
	public ResponseEntity<CustomResponse> save(@RequestBody AssignTask assignTask){
		
		CustomResponse response = assignTaskService.save(assignTask);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/getuserlist")
	public ResponseEntity<CustomResponse> getAllUser(){
		CustomResponse response = assignTaskService.getAllUser();
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/getTaskList")
	public ResponseEntity<CustomResponse> getTaskList(){
		CustomResponse response = assignTaskService.getTaskList();
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/getTaskByEmployee/{username}")
	public ResponseEntity<?> getTaskByEmployee(@PathVariable String username) {

	    List<TaskListDTO> tasks = assignTaskService.getTaskByEmployee(username);
        
	    if(tasks.isEmpty()) {
	    	ResponseEntity.ok(new CustomResponse<>("Tasks list Empty",400, tasks));
	    }
	    
	    return ResponseEntity.ok(
	        new CustomResponse<>("Tasks Fetch Successfully",200, tasks)
	    );
	}
	
	@GetMapping("/getTaskById/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Long id){
		CustomResponse taskdtl = assignTaskService.getTaskById(id);
		return ResponseEntity.ok(new CustomResponse("Task fetch successfully", 200, taskdtl));
		
	}
}
