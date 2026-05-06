package com.phsc.taskmate.dto;

public class RoleDto {

	private String roleName;
	
	public RoleDto() {
		super();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleDto [roleName=" + roleName + "]";
	}

	

}
