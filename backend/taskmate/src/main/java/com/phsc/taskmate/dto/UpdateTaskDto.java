package com.phsc.taskmate.dto;

import java.time.LocalDate;

import com.phsc.taskmate.enums.TaskStatus;

public class UpdateTaskDto {

	private TaskStatus taskStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private String remarks;
    
    

	public UpdateTaskDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public UpdateTaskDto(TaskStatus taskStatus, LocalDate startDate, LocalDate endDate, String remarks) {
		super();
		this.taskStatus = taskStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remarks = remarks;
	}




	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    

	
}
