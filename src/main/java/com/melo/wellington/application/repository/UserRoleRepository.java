package com.melo.wellington.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melo.wellington.application.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
