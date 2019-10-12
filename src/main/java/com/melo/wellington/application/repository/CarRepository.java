package com.melo.wellington.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melo.wellington.application.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<Car> findFirstByLicensePlate(String licensePlate);
}
