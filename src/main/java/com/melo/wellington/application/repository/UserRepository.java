package com.melo.wellington.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melo.wellington.application.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByLogin(String login);
	
	Optional<User> findFirstByEmail(String email);
}
