package com.melo.wellington.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.melo.wellington.application.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByLogin(String login);
	
	Optional<User> findFirstByEmail(String email);
	
	@Modifying
	@Query("update User u set u.lastLogin = sysdate WHERE u.login = :login")
	void updateLastLogin(@Param("login") String login);

}
