package com.phsc.taskmate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phsc.taskmate.dto.RegisterUserDTO;
import com.phsc.taskmate.entity.UserRegister;

@Repository
public interface UserRepository extends JpaRepository<UserRegister, Long>{

	                                                   
	@Query(value = "SELECT username, role, status FROM registeruser WHERE username = :name",nativeQuery = true)
    List<RegisterUserDTO> findByUsername(@Param("name") String st);
	
	@Query(value = "SELECT name,username, password, role, status FROM registeruser WHERE username = :name",nativeQuery = true)
	RegisterUserDTO findByUser(@Param("name") String st);

	@Query("SELECT new com.phsc.taskmate.entity.UserRegister(u.id, u.name, u.username, u.phone, u.designation, u.department, u.gender) FROM UserRegister u WHERE u.status = 'ACTIVE'")
	List<UserRegister> getAllUserByStatus();
	
}
