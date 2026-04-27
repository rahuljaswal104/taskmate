package com.phsc.taskmate.dto;

public class RegisterUserDTO {

	private String name;

	private String username;

	private String password;

	private String role;

	private String status;

	private String gender;


	public RegisterUserDTO(String username, String role, String status) {
		super();
		this.username = username;
		this.role = role;
		this.status = status;
	}
	

	public RegisterUserDTO(String username, String password, String role, String status) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
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

	@Override
	public String toString() {
		return "RegisterUserDTO [name=" + name + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", status=" + status + ", gender=" + gender + "]";
	}

}
