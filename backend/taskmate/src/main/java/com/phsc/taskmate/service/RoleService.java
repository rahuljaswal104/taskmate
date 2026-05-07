package com.phsc.taskmate.service;

import java.util.List;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RoleDto;
import com.phsc.taskmate.entity.Role;

public interface RoleService {

	public CustomResponse roleSave(RoleDto roleDto);

	public CustomResponse getRoleList();
}
