package com.phsc.taskmate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUserDTO {

	@NotBlank(message = "Name is required")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name can contain only letters and spaces (no digits or special characters)")
	@Size(min = 3, message = "Name atleast 3 character")
	private String name;

	@NotBlank(message = "Username (email) is required")
	@Email(message = "Username must be a valid email")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(min = 5, message = "Password must be at least 8 characters long")
	private String password;

	private String role;

	private String status;

	private String gender;

	private String phone;

	private String designation;

	private String department;

	public RegisterUserDTO() {
		super();
	}

	public RegisterUserDTO(
			@NotBlank(message = "Name is required") @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can contain only letters and spaces (no digits or special characters)") @Size(min = 3, message = "Name atleast 3 character") String name,
			@NotBlank(message = "Username (email) is required") @Email(message = "Username must be a valid email") String username,
			@NotBlank(message = "Password is required") @Size(min = 5, message = "Password must be at least 8 characters long") String password,
			String role, String status, String gender, String phone, String designation, String department) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
		this.gender = gender;
		this.phone = phone;
		this.designation = designation;
		this.department = department;
	}

	public RegisterUserDTO(

			@NotBlank(message = "Name is required") @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can contain only letters and spaces (no digits or special characters)") @Size(min = 3, message = "Name atleast 3 character") String name,

			@NotBlank(message = "Username (email) is required") @Email(message = "Username must be a valid email") String username,

			@NotBlank(message = "Password is required") @Size(min = 5, message = "Password must be at least 8 characters long") String password,

			String role,

			String status

	) {

		super();

		this.name = name;

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

	@Override
	public String toString() {
		return "RegisterUserDTO [name=" + name + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", status=" + status + ", gender=" + gender + ", phone=" + phone + ", designation=" + designation
				+ ", department=" + department + "]";
	}

}
