package com.phsc.taskmate.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registeruser")
public class TaskMateRegisterUser {

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

	@Column(name = "role")
	private String role; // e.g., USER, ADMIN

	@Column(name = "status")
	private String status; // e.g., ACTIVE, INACTIVE

	@Column(name = "gender")
	private String gender;

	public TaskMateRegisterUser() {
		super();
		// TODO Auto-generated constructor stub
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

	public TaskMateRegisterUser(Long id, String name, String username, String password, String role, String status,
			String gender) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
		this.gender = gender;
	}


//	username, password role, status
	
	
	

	@Override
	public String toString() {
		return "TaskMateRegisterUser [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", status=" + status + ", gender=" + gender + "]";
	}

public TaskMateRegisterUser(String username, String password, String role, String status) {
	super();
	this.username = username;
	this.password = password;
	this.role = role;
	this.status = status;
}

}
