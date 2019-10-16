package com.melo.wellington.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.melo.wellington.application.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	@Query(value = "SELECT u FROM UserRole u WHERE u.user.id = :userId")
	List<UserRole> findByUserId(@Param("userId") Long userId);
}
