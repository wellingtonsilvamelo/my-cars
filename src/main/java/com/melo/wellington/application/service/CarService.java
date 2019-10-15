package com.melo.wellington.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melo.wellington.application.builder.entity.CarBuilder;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.exception.ApiException;
import com.melo.wellington.application.repository.CarRepository;

@Service
@Transactional(rollbackOn=Exception.class)
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
		
	public List<Car> getAllCars(){
		return carRepository.findAll();
	}
	
	public List<Car> getAllCarsByUser(User user){
		List<Car> usersList = carRepository.findByUser(user);
		return usersList;
	}
	
	public Car saveCar(Car car){
		
		Optional<Car> carExists = carRepository.findFirstByLicensePlate(car.getLicensePlate());
		
		if(carExists.isPresent() && carExists.get().getId() != car.getId()) {
			throw new ApiException("License plate already exists!");
		}
		
		car = carRepository.save(car);
		
		return car;
	}
	
	public List<Car> saveAllCar(List<Car> cars){		
		List<Car> savedCars = cars.stream()
				.map(c -> saveCar(c))
				.collect(Collectors.toList());
		
		return savedCars;
	}

	
	public Car getCar(Long id){
		Optional<Car> result = carRepository.findById(id); 
		if(result.isPresent()) {
			Car car = result.get();
			car.setQtdUtilizacao(car.getQtdUtilizacao()+1);
			return car;
		}
		throw new ApiException("Car not found!");		
	}

	
	public void removeCar(Long id){
		Optional<Car> exists = carRepository.findById(id);
		
		if (exists.isPresent()) {
			carRepository.deleteById(id);
		}else {
			throw new ApiException("Car not found!");			
		}		
	}
	
	public void removeAllCars(List<Car> cars){
		cars.forEach(c -> removeCar(c.getId()));
	}
	
	public Car updateCar(Car car){
		
		Optional<Car> exists = carRepository.findById(car.getId());
		
		if(exists.isPresent()) {
			
			Optional<Car> licensePlateExists = carRepository.findFirstByLicensePlate(car.getLicensePlate());
			
			if(licensePlateExists.isPresent()) {
				throw new ApiException("License plate already exists!");
			}
			
			car = CarBuilder.create()
					.color(car.getColor())
					.id(exists.get().getId())
					.licensePlate(exists.get().getLicensePlate())
					.model(car.getModel())
					.user(car.getUser())
					.year(car.getYear())
					.build();
			
			return carRepository.save(car);
		}
		
		throw new ApiException("Car not found!");
	}


}
