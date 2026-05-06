package com.phsc.taskmate.service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RoleDto;

public interface RoleService {

	public CustomResponse roleSave(RoleDto roleDto);
}
