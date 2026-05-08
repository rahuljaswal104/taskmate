package com.phsc.taskmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phsc.taskmate.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	
	@Query(value = "SELECT * FROM projects WHERE project_name = :projectName",nativeQuery = true)
    Project findByProjectName(@Param("projectName") String projectName);

}
