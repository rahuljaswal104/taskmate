package com.phsc.taskmate.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phsc.taskmate.customresponse.CustomResponse;
import com.phsc.taskmate.entity.Department;
import com.phsc.taskmate.repository.DepartmentRepository;
import com.phsc.taskmate.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentrRepo;

	@Override
	public List<Department> getAll() {
		return departmentrRepo.findAll();
	}

	@Override
	public CustomResponse save(Department d) {
		if (d == null) {
			return new CustomResponse("Please Enter Department Name");
		}
		
		Department deptexist = departmentrRepo.findByDepartmentName(d.getDepartmentName());
		if(deptexist!= null) {
			return new CustomResponse("Department Already exist");
		}
		
		Department dept = new Department();
		dept.setDepartmentCode(d.getDepartmentCode());
		dept.setDepartmentName(d.getDepartmentName());
		departmentrRepo.save(dept);
		
		
		return new CustomResponse("Deparment saved successfully");
	}

}
