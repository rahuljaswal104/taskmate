package com.phsc.taskmate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.phsc.taskmate.dto.EmployeeReportDTO;
import com.phsc.taskmate.dto.TaskListDTO;
import com.phsc.taskmate.entity.AssignTask;
import com.phsc.taskmate.enums.TaskStatus;

@Repository
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long> {

	@Query(value = "select * from assign_tasks where title =:title", nativeQuery = true)
	AssignTask findByTasktitle(@Param("title") String title);

	@Query("SELECT new com.phsc.taskmate.dto.TaskListDTO(t.title, u.name, t.assignedBy, t.assignedDate, t.targetDate, t.taskStatus, t.project) FROM AssignTask t JOIN t.employees u")
	List<TaskListDTO> getTaskListData();
	
	@Query("SELECT new com.phsc.taskmate.dto.TaskListDTO(t.title,t.id, u.name, t.assignedBy, t.assignedDate, t.targetDate, t.taskStatus, t.project) FROM AssignTask t JOIN t.employees u where u.username =:username")
	List<TaskListDTO> getTaskByUserName(@Param("username")String username);

	 Long countByTaskStatus(TaskStatus taskStatus);

	 @Query("SELECT DISTINCT new com.phsc.taskmate.dto.TaskListDTO(t.id, t.title, t.project, emp.name, t.assignedBy, t.assignedDate, t.targetDate, t.taskStatus) FROM AssignTask t JOIN t.employees emp WHERE emp.department.id = :departmentId")
	 List<TaskListDTO> getTaskListByDepartment(@Param("departmentId") Long departmentId);
	 
	 @Query("SELECT new com.phsc.taskmate.dto.EmployeeReportDTO(u.name, COUNT(t), SUM(CASE WHEN t.taskStatus = 'COMPLETED' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus = 'PENDING' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus = 'IN_PROGRESS' THEN 1 ELSE 0 END), ROUND((SUM(CASE WHEN t.taskStatus = 'COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(t)), 1)) FROM AssignTask t JOIN t.employees u GROUP BY u.name")
	 List<EmployeeReportDTO> getEmployeeReport();
	 
	 @Query("SELECT new com.phsc.taskmate.dto.EmployeeReportDTO(u.name, COUNT(t), SUM(CASE WHEN t.taskStatus='COMPLETED' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus='PENDING' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus='IN_PROGRESS' THEN 1 ELSE 0 END), ROUND((SUM(CASE WHEN t.taskStatus='COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(t)), 1)) FROM AssignTask t JOIN t.employees u WHERE u.department.id = :id GROUP BY u.id, u.name")
	 List<EmployeeReportDTO> getDepartmentReport(@Param("id") Long id);

	 @Query("SELECT new com.phsc.taskmate.dto.EmployeeReportDTO(u.name, COUNT(t), SUM(CASE WHEN t.taskStatus='COMPLETED' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus='PENDING' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus='IN_PROGRESS' THEN 1 ELSE 0 END), ROUND((SUM(CASE WHEN t.taskStatus='COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(t)), 1)) FROM AssignTask t JOIN t.employees u WHERE u.department.id = :id AND u.role.roleName IN ('MANAGER','EMPLOYEE') GROUP BY u.id, u.name")
	 List<EmployeeReportDTO> getManagerDepartmentReport(@Param("id") Long id);
	 
	 
	 @Query("SELECT COUNT(DISTINCT t) FROM AssignTask t JOIN t.employees e WHERE e.department.id = :departmentId AND t.taskStatus = :taskStatus")
	 Long countByDepartmentAndTaskStatus(@Param("departmentId") Long departmentId,
	                                     @Param("taskStatus") TaskStatus taskStatus);

	

}
