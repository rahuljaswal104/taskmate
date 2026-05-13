package com.phsc.taskmate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phsc.taskmate.entity.AssignTask;

@Repository
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long> {

	@Query(value = "select * from assign_tasks where title =:title", nativeQuery = true)
	AssignTask findByTasktitle(@Param("title") String title);

	@Query(value = """
			SELECT employee_name, COUNT(*) AS totalTasks,

			    SUM(CASE WHEN task_status = 'COMPLETED' THEN 1 ELSE 0 END) AS completedTasks,

			    SUM(CASE WHEN task_status = 'PENDING' THEN 1 ELSE 0 END) AS pendingTasks,

			    SUM(CASE WHEN task_status = 'IN_PROGRESS' THEN 1 ELSE 0 END) AS inProgressTasks,

			    ROUND((SUM(CASE WHEN task_status = 'COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)),
			    
			    1) AS completionPercentage FROM assign_tasks GROUP BY employee_name
			""", nativeQuery = true)
	List<Object[]> getEmployeeReport();

	//Long countByTaskStatus(com.phsc.taskmate.enums.TaskStatus taskStatus);
}
