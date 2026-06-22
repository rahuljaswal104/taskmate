package com.phsc.taskmate.dto;

import java.time.LocalDate;

import com.phsc.taskmate.entity.Project;
import com.phsc.taskmate.entity.UserRegister;
import com.phsc.taskmate.enums.TaskStatus;

public class TaskListDTO {

	private String title;
	private Long id;
	private String name;
	private UserRegister assignedBy;
	private LocalDate assignedDate;
	private LocalDate targetDate;
	private TaskStatus taskStatus;
	private Project project;
	private Long empId;

	public TaskListDTO() {
		super();
	}

	public TaskListDTO(String title, String name, UserRegister assignedBy, LocalDate assignedDate, LocalDate targetDate,
			TaskStatus taskStatus, Project project) {
		super();
		this.title = title;
		this.name = name;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.targetDate = targetDate;
		this.taskStatus = taskStatus;
		this.project = project;
	}
	
	public TaskListDTO(String title,Long id, String name, UserRegister assignedBy, LocalDate assignedDate, LocalDate targetDate,
			TaskStatus taskStatus, Project project) {
		super();
		this.title = title;
		this.id= id;
		this.name = name;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.targetDate = targetDate;
		this.taskStatus = taskStatus;
		this.project = project;
	}
	
	public TaskListDTO(
	        Long id,
	        String title,
	        Project project,
	        Long empId,
	        String name,
	        UserRegister assignedBy,
	        LocalDate assignedDate,
	        LocalDate targetDate,
	        TaskStatus taskStatus) {

	    this.id = id;
	    this.title = title;
	    this.project = project;
	    this.empId = empId;
	    this.name = name;
	    this.assignedBy = assignedBy;
	    this.assignedDate = assignedDate;
	    this.targetDate = targetDate;
	    this.taskStatus = taskStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRegister getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(UserRegister assignedBy) {
		this.assignedBy = assignedBy;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	
	

}
