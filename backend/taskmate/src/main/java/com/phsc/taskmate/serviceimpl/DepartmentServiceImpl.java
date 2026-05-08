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
		
		if (d.getDepartmentCode() == null ||d.getDepartmentCode().isEmpty()) {
			return new CustomResponse("Please Enter Department code");
		}
		
		if (d.getDepartmentName() == null ||d.getDepartmentName().isEmpty()) {
			return new CustomResponse("Please Enter Department Name");
		}
		
		
		Department deptexist = departmentrRepo.findByDepartmentName(d.getDepartmentName());
		if(deptexist!= null) {
			return new CustomResponse("Department Already exist");
		}
		
		Department codeExist = departmentrRepo.findByDepartmentCode(d.getDepartmentCode());

		if(codeExist != null){
		    return new CustomResponse("Department code already exists");
		}
		
//		Department dept = new Department();
//		dept.setDepartmentCode(d.getDepartmentCode());
//		dept.setDepartmentName(d.getDepartmentName());
		departmentrRepo.save(d);
		
		
		return new CustomResponse("Deparment saved successfully");
	}

}
