package com.phsc.taskmate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String departmentCode;

	@Column(nullable = false)
	private String departmentName;

	@Column(nullable = false)
	private boolean active = true;
	
	@CreationTimestamp
	private  LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Department(String departmentCode, String departmentName) {
		super();
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentCode=" + departmentCode + ", departmentName=" + departmentName
				+ ", active=" + active + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	
	
}
