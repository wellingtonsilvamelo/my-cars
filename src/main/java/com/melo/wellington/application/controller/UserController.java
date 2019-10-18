package com.melo.wellington.application.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.builder.dto.UserDTOBuilder;
import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.exception.ApiException;
import com.melo.wellington.application.resource.UserResource;
import com.melo.wellington.application.service.CarService;
import com.melo.wellington.application.service.UserService;
import com.melo.wellington.application.util.Util;

@RestController
public class UserController implements UserResource{
	
	private UserService userService;
	
	private CarService carService;
	
	@Autowired
	public UserController(UserService userService, CarService carService) {
		this.userService = userService;
		this.carService = carService;
	}

	@Override
	public ResponseEntity<List<UserDTO>> getAll() {		
		List<User> users = userService.getAllUsers();
		
		List<UserDTO> userDTOList = users.stream()
				.map(u -> UserDTOBuilder.create()
						.id(u.getId())
						.firstName(u.getFirstName())
						.lastName(u.getLastName())
						.birthday(u.getBirthday())
						.createdAt(u.getCreatedAt())
						.email(u.getEmail())
						.lastLogin(u.getLastLogin())
						.cars(carService.getAllCarsByUser(u))
						.login(u.getLogin())
						.phone(u.getPhone())
						.build())
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(Util.comparingUsers(userDTOList));
	}

	@Override
	public ResponseEntity<?> save(UserDTO userDTO) {
		
		User user = UserBuilder.create()
				.firstName(userDTO.getFirstName())
				.lastName(userDTO.getLastName())
				.email(userDTO.getEmail())
				.birthday(userDTO.getBirthday())
				.login(userDTO.getLogin())
				.password(userDTO.getPassword())
				.phone(userDTO.getPhone())
				.createdAt(LocalDate.now())
				.cars(userDTO.getCars())
				.build();
		
		user = userService.saveUser(user);
		
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<UserDTO> getUser(Long userId) {
		
		User user = userService.getUser(userId);
		List<Car> cars = carService.getAllCarsByUser(user);
		
		UserDTO userDTO = UserDTOBuilder.create()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.birthday(user.getBirthday())
				.createdAt(user.getCreatedAt())
				.email(user.getEmail())
				.lastLogin(user.getLastLogin())
				.login(user.getLogin())
				.phone(user.getPhone())
				.cars(cars)
				.build();		
		
		return ResponseEntity.ok(userDTO);
	}

	@Override
	public ResponseEntity<?> deleteUser(Long userId) {
		userService.removeUser(userId);		
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> updateUser(UserDTO userDTO, Long userId) {
		
		User user = UserBuilder.create()
				.id(userId)
				.firstName(userDTO.getFirstName())
				.lastName(userDTO.getLastName())
				.email(userDTO.getEmail())
				.birthday(userDTO.getBirthday())
				.phone(userDTO.getPhone())
				.createdAt(userDTO.getCreatedAt())
				.build();
		
		user = userService.updateUser(user);
		
		return ResponseEntity.ok().build();
	}

}
