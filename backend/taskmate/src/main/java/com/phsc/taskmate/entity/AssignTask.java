package com.phsc.taskmate.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.phsc.taskmate.enums.Priority;
import com.phsc.taskmate.enums.TaskStatus;
import com.phsc.taskmate.enums.TaskType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "assign_tasks")
public class AssignTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Task Details
	private String title;

	private String project;

	@Enumerated(EnumType.STRING)
	private TaskType taskType;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Lob
	private String description;

	// Employee Details
	private Long employeeId;

	private String employeeName;

	private String designation;

	private String department;

	// Assignment Details
	private String assignedBy;

	private LocalDate assignedDate;

	private LocalDate startDate;

	private LocalDate endDate;

	// Progress Tracking
	private Integer progressPercentage = 0;

	private LocalDate completedDate;

	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;

	@Lob
	private String remarks;

	// Status
	private String status = "ACTIVE";

	// Audit Fields
	@CreationTimestamp
	private LocalDateTime createdDate;
	
    @UpdateTimestamp
	private LocalDateTime updatedDate;

	public AssignTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignTask(Long id, String title, String project, TaskType taskType, Priority priority, String description,
			Long employeeId, String employeeName, String designation, String department, String assignedBy,
			LocalDate assignedDate, LocalDate startDate, LocalDate endDate, Integer progressPercentage,
			LocalDate completedDate, TaskStatus taskStatus, String remarks, String status, LocalDateTime createdDate,
			LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.title = title;
		this.project = project;
		this.taskType = taskType;
		this.priority = priority;
		this.description = description;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.designation = designation;
		this.department = department;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.progressPercentage = progressPercentage;
		this.completedDate = completedDate;
		this.taskStatus = taskStatus;
		this.remarks = remarks;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public Integer getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(Integer progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public LocalDate getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(LocalDate completedDate) {
		this.completedDate = completedDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "AssignTask [id=" + id + ", title=" + title + ", project=" + project + ", taskType=" + taskType
				+ ", priority=" + priority + ", description=" + description + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", designation=" + designation + ", department=" + department
				+ ", assignedBy=" + assignedBy + ", assignedDate=" + assignedDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", progressPercentage=" + progressPercentage + ", completedDate="
				+ completedDate + ", taskStatus=" + taskStatus + ", remarks=" + remarks + ", status=" + status
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	
	
}