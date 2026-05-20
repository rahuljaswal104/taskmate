package com.phsc.taskmate.dto;

import java.time.LocalDate;

import com.phsc.taskmate.enums.TaskStatus;

public class TaskListDTO {

	private String title;
	private Long id;
	private String name;
	private String assignedBy;
	private LocalDate assignedDate;
	private LocalDate endDate;
	private TaskStatus taskStatus;
	private String project;

	public TaskListDTO() {
		super();
	}

	public TaskListDTO(String title, String name, String assignedBy, LocalDate assignedDate, LocalDate endDate,
			TaskStatus taskStatus, String project) {
		super();
		this.title = title;
		this.name = name;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.endDate = endDate;
		this.taskStatus = taskStatus;
		this.project = project;
	}
	
	public TaskListDTO(String title,Long id, String name, String assignedBy, LocalDate assignedDate, LocalDate endDate,
			TaskStatus taskStatus, String project) {
		super();
		this.title = title;
		this.id= id;
		this.name = name;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.endDate = endDate;
		this.taskStatus = taskStatus;
		this.project = project;
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

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
