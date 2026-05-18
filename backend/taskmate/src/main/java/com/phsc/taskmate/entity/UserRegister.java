package com.phsc.taskmate.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "registeruser")
public class UserRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	private String phone;
	
	private String designation;
	
	private String department;
	
	private String employeeCode;

	@Column(name = "role")
	private String role; // e.g., USER, ADMIN

	@Column(name = "status")
	private String status = "ACTIVE"; // e.g., ACTIVE, INACTIVE

	@Column(name = "gender")
	private String gender;

	
	@ManyToMany(mappedBy = "employees")
	private List<AssignTask> tasks;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegister(Long id, String name, String username, String password, String phone,
			String designation, String department, String employeeCode, String role, String status, String gender,
			List<AssignTask> tasks, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.designation = designation;
		this.department = department;
		this.employeeCode = employeeCode;
		this.role = role;
		this.status = status;
		this.gender = gender;
		this.tasks = tasks;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public UserRegister(Long id, String name, String username, String phone, String designation, String department,
			String gender) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.phone = phone;
		this.designation = designation;
		this.department = department;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<AssignTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<AssignTask> tasks) {
		this.tasks = tasks;
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
		return "TaskMateRegisterUser [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", phone=" + phone + ", designation=" + designation + ", department=" + department + ", employeeCode="
				+ employeeCode + ", role=" + role + ", status=" + status + ", gender=" + gender + ", tasks=" + tasks
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	
	
}
