package com.phsc.taskmate.service;

import java.util.List;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.entity.Department;

public interface DepartmentService {
	
	List<Department> getAll();
	
	CustomResponse save(Department d);
	
	//CustomResponse delete(Long id);

}
