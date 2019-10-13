package com.melo.wellington.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;

public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<Car> findFirstByLicensePlate(String licensePlate);
	
	List<Car> findByUser(User userId);
}
