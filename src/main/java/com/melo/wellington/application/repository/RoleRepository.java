package com.melo.wellington.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melo.wellington.application.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
