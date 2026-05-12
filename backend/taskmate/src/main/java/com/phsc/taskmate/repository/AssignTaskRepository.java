package com.phsc.taskmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phsc.taskmate.entity.AssignTask;

@Repository
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long> {
	
	@Query(value= "select * from assign_tasks where title =:title",
			nativeQuery=true)
	AssignTask findByTasktitle(@Param("title") String title);
}
