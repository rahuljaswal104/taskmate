package com.phsc.taskmate.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.dto.RoleDto;
import com.phsc.taskmate.entity.Role;
import com.phsc.taskmate.repository.RoleRepository;
import com.phsc.taskmate.service.RoleService;

@Service
public class RoleSeviceImpl implements RoleService{
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public CustomResponse roleSave(RoleDto roleDto) {
		
		if(roleDto == null) {
			return new CustomResponse("role null",400, roleDto);
		}
		
		 Role rolePresent = roleRepository.findByRoleName(roleDto.getRoleName());
		
		if(rolePresent != null) {
			return new CustomResponse("role is already exist",409, roleDto.getRoleName());
		}
		
		Role role = new Role();
		role.setRoleName(roleDto.getRoleName());
		
		roleRepository.save(role);
		
		return new CustomResponse("role saved successfully", 200, role);
	}


	@Override
	public CustomResponse getRoleList() {
		List<Role> roleList = roleRepository.findAll();
		if(roleList.isEmpty()) {
			return new CustomResponse("list empty", 200 ,roleList);
		}
		return new CustomResponse("success", 200 ,roleList);
	}

}
