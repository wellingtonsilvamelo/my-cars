package com.melo.wellington.application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.builder.dto.UserDTOBuilder;
import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.resource.UserResource;
import com.melo.wellington.application.service.CarService;
import com.melo.wellington.application.service.UserService;

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
						.login(u.getLogin())
						.phone(u.getPhone())
						.build())
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(userDTOList);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDTO> updateUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
