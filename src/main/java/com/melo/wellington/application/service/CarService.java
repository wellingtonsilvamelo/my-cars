package com.melo.wellington.application.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melo.wellington.application.builder.entity.CarBuilder;
import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.exception.ApiException;
import com.melo.wellington.application.repository.CarRepository;

@Service
@Transactional(rollbackOn=Exception.class)
@SuppressWarnings("unused")
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
		
	private List<Car> getAllCars(){
		return carRepository.findAll();
	}
	
	private Car saveCar(Car car){
		
		Optional<Car> carExists = carRepository.findFirstByLicensePlate(car.getLicensePlate());
		
		if(carExists.isPresent()) {
			throw new ApiException("License plate already exists!");
		}
		
		return carRepository.save(car);
	}

	
	private Car getCar(Long id){
		Optional<Car> result = carRepository.findById(id); 
		if(result.isPresent()) {
			return result.get();
		}
		throw new ApiException("Car not found!");		
	}

	
	private void removeCar(Long id){
		carRepository.deleteById(id);
	}

	
	private Car updateCar(Car car){
		
		Optional<Car> exists = carRepository.findById(car.getId());
		
		if(exists.isPresent()) {
			Car aux = exists.get();
			
			Optional<Car> licensePlateExists = carRepository.findFirstByLicensePlate(car.getLicensePlate());
			
			if(licensePlateExists.isPresent()) {
				throw new ApiException("License plate already exists!");
			}
			
			aux = CarBuilder.create()
					.color(car.getColor())
					.id(aux.getId())
					.licensePlate(car.getLicensePlate())
					.model(car.getModel())
					.user(car.getUser())
					.year(car.getYear())
					.build();
			
			return carRepository.save(aux);
		}
		
		throw new ApiException("Car not found!");
	}


}
