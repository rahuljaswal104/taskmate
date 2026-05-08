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
import com.phsc.taskmate.dto.ProjectDto;
import com.phsc.taskmate.service.ProjectService;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	 @Autowired
	 private ProjectService projectService; 
	
	@PostMapping("/save")
    public ResponseEntity<CustomResponse> saveProject(@RequestBody ProjectDto projectDto) {
		
		CustomResponse response =  projectService.saveProject(projectDto);
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/projectList")
	public ResponseEntity<CustomResponse> getProjectList(){

	    CustomResponse response = projectService.getProjectList();

	    return ResponseEntity.ok(response);
	}

}
