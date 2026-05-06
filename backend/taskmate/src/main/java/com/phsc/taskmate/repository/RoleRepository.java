package com.phsc.taskmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phsc.taskmate.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(value = "SELECT * FROM roles WHERE role_name = :name", nativeQuery = true)
	Role findByRoleName(@Param("name") String name);

}
