package com.phsc.taskmate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phsc.taskmate.dto.EmployeeReportDTO;
import com.phsc.taskmate.dto.TaskListDTO;
import com.phsc.taskmate.entity.AssignTask;

@Repository
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long> {

	@Query(value = "select * from assign_tasks where title =:title", nativeQuery = true)
	AssignTask findByTasktitle(@Param("title") String title);

	@Query("SELECT new com.phsc.taskmate.dto.EmployeeReportDTO(u.name, COUNT(t), SUM(CASE WHEN t.taskStatus = 'COMPLETED' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus = 'PENDING' THEN 1 ELSE 0 END), SUM(CASE WHEN t.taskStatus = 'IN_PROGRESS' THEN 1 ELSE 0 END), ROUND((SUM(CASE WHEN t.taskStatus = 'COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(t)), 1)) FROM AssignTask t JOIN t.employees u GROUP BY u.name")
	List<EmployeeReportDTO> getEmployeeReport();

	@Query("SELECT new com.phsc.taskmate.dto.TaskListDTO(t.title, u.name, t.assignedBy, t.assignedDate, t.endDate, t.taskStatus, t.project) FROM AssignTask t JOIN t.employees u")
	List<TaskListDTO> getTaskListData();
	
	@Query("SELECT new com.phsc.taskmate.dto.TaskListDTO(t.title,t.id, u.name, t.assignedBy, t.assignedDate, t.endDate, t.taskStatus, t.project) FROM AssignTask t JOIN t.employees u where u.username =:username")
	List<TaskListDTO> getTaskByUserName(@Param("username")String username);

}
