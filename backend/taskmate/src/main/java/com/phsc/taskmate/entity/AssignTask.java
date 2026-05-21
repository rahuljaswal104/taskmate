package com.phsc.taskmate.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.phsc.taskmate.enums.Priority;
import com.phsc.taskmate.enums.TaskStatus;
import com.phsc.taskmate.enums.TaskType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "assign_tasks")
public class AssignTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Task Details
	@NotBlank
	private String title;

	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	@Enumerated(EnumType.STRING)
	private TaskType taskType;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Column(columnDefinition = "TEXT")
	private String description;

	// Employee Details
//	private Long employeeId;
//
//	private String employeeName;
//
//	private String designation;
//
//	private String department;
	
	
	@ManyToMany
	@JoinTable(
			name="employee_tasks",
			joinColumns = @JoinColumn(name ="task_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id")
			)
	private List<UserRegister> employees;

	// Assignment Details
	
	@ManyToOne
	@JoinColumn(name="assign_by")
	private UserRegister assignedBy;

	private LocalDate assignedDate;

	private LocalDate startDate;

	private LocalDate endDate;

	// Progress Tracking
	private Integer progressPercentage = 0;

	private LocalDate targetDate;

	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus = TaskStatus.PENDING ;

	@Column(columnDefinition = "TEXT")
	private String remarks;
	
	  private String fileName;

	    // File type مثل image/png, application/pdf
	  private String fileType;

	    // File path OR URL
	  private String filePath;

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
	
	public AssignTask(Long id, @NotBlank String title, Project project, TaskType taskType, Priority priority,
			String description, List<UserRegister> employees, UserRegister assignedBy, LocalDate assignedDate,
			LocalDate startDate, LocalDate endDate, Integer progressPercentage, LocalDate targetDate,
			TaskStatus taskStatus, String remarks, String fileName, String fileType, String filePath, String status,
			LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.title = title;
		this.project = project;
		this.taskType = taskType;
		this.priority = priority;
		this.description = description;
		this.employees = employees;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.progressPercentage = progressPercentage;
		this.targetDate = targetDate;
		this.taskStatus = taskStatus;
		this.remarks = remarks;
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}




	public AssignTask(Long id, String title, Project project, TaskType taskType, Priority priority, String description,
			List<UserRegister> employees, UserRegister assignedBy, LocalDate assignedDate, LocalDate startDate,
			LocalDate endDate, Integer progressPercentage, LocalDate targetDate, TaskStatus taskStatus,
			String remarks, String status, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.title = title;
		this.project = project;
		this.taskType = taskType;
		this.priority = priority;
		this.description = description;
		this.employees = employees;
		this.assignedBy = assignedBy;
		this.assignedDate = assignedDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.progressPercentage = progressPercentage;
		this.targetDate = targetDate;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
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

	public List<UserRegister> getEmployees() {
		return employees;
	}

	public void setEmployees(List<UserRegister> employees) {
		this.employees = employees;
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

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}





	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFilePath() {
		return filePath;
	}





	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
				+ ", priority=" + priority + ", description=" + description + ", employees=" + employees
				+ ", assignedBy=" + assignedBy + ", assignedDate=" + assignedDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", progressPercentage=" + progressPercentage + ", targetDate=" + targetDate
				+ ", taskStatus=" + taskStatus + ", remarks=" + remarks + ", fileName=" + fileName + ", fileType="
				+ fileType + ", filePath=" + filePath + ", status=" + status + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}