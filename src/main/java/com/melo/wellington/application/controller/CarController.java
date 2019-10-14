package com.melo.wellington.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.builder.dto.CarDTOBuilder;
import com.melo.wellington.application.builder.dto.UserDTOBuilder;
import com.melo.wellington.application.builder.entity.CarBuilder;
import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.resource.CarResource;
import com.melo.wellington.application.service.CarService;
import com.melo.wellington.application.service.UserService;

@RestController
public class CarController implements CarResource{
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<List<CarDTO>> getAll(@RequestHeader("Authorization") String authorization) {
		
		//TODO - obter usuário da token;
		User user = UserBuilder.create()
				.id(1l)
				.build();		

		List<Car> cars = carService.getAllCarsByUser(user);
		
		List<CarDTO> userDTOList = cars.stream()
				.map(car -> CarDTOBuilder.create()
						.color(car.getColor())
						.id(car.getId())
						.licensePlate(car.getLicensePlate())
						.model(car.getModel())
						.userId(car.getUser().getId())
						.year(car.getYear())
						.build())
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(userDTOList);
	}

	@Override
	public ResponseEntity<CarDTO> save(CarDTO carDTO) {
		
		Car car = CarBuilder.create()
				.color(carDTO.getColor())
				.id(carDTO.getId())
				.licensePlate(carDTO.getLicensePlate())
				.model(carDTO.getModel())
				.user(UserBuilder.create().id(carDTO.getUserId()).build())
				.year(carDTO.getYear())				
				.build();
		
		car = carService.saveCar(car);
		carDTO.setId(car.getId());
		
		return ResponseEntity.ok(carDTO);
	}

	@Override
	public ResponseEntity<CarDTO> getCar(Long userId) {

		Car car = carService.getCar(userId);
		
		CarDTO carDTO = CarDTOBuilder.create()
				.color(car.getColor())
				.id(car.getId())
				.licensePlate(car.getLicensePlate())
				.model(car.getModel())
				.userId(car.getUser().getId())
				.year(car.getYear())
				.build();

		return ResponseEntity.ok(carDTO);
	}

	@Override
	public ResponseEntity<?> deleteCar(Long userId) {
		carService.removeCar(userId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<CarDTO> updateCar(CarDTO carDTO, Long carId) {
		
		Car car = carService.getCar(carId);
		
		car = carService.saveCar(CarBuilder.create()
				.color(carDTO.getColor())
				.id(car.getId())
				.licensePlate(car.getLicensePlate())
				.model(carDTO.getModel())
				.user(UserBuilder.create().id(car.getUser().getId()).build())
				.year(carDTO.getYear())				
				.build());
		
		carDTO = CarDTOBuilder.create()
				.color(car.getColor())
				.id(car.getId())
				.model(car.getModel())
				.userId(car.getUser().getId())
				.year(car.getYear())
				.build();
		
		return ResponseEntity.ok(carDTO);
	}

}
