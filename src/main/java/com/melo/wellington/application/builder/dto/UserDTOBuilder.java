package com.melo.wellington.application.builder.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.entity.Car;

public class UserDTOBuilder {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String password;
	private String phone;
	private LocalDate createdAt;
	private LocalDateTime lastLogin;
	private List<CarDTO> cars = new ArrayList<>();
	
	public static UserDTOBuilder create() {
		return new UserDTOBuilder();
	}
	
	public UserDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public UserDTOBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public UserDTOBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public UserDTOBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public UserDTOBuilder birthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}
	
	public UserDTOBuilder login(String login) {
		this.login = login;
		return this;
	}
	
	public UserDTOBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public UserDTOBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public UserDTOBuilder createdAt(LocalDate createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public UserDTOBuilder lastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}
	
	public UserDTOBuilder cars(List<Car> cars) {
		List<CarDTO> carsDTOList = cars.stream()
			.map(c -> CarDTOBuilder.create()
					.id(c.getId())
					.userId(c.getUser().getId())
					.licensePlate(c.getLicensePlate())
					.model(c.getModel())
					.qtdUtilizacao(c.getQtdUtilizacao())
					.color(c.getColor())
					.year(c.getYear())
					.build()
			).collect(Collectors.toList());
		
		carsDTOList.sort(Comparator.comparingInt(CarDTO::getQtdUtilizacao).reversed()
				.thenComparing(CarDTO::getModel));
		
		this.cars = carsDTOList;
		return this;
	}
	
	public UserDTO build() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setFirstName(firstName);
		userDTO.setLastName(lastName);
		userDTO.setEmail(email);
		userDTO.setBirthday(birthday);
		userDTO.setLogin(login);
		userDTO.setPassword(password);
		userDTO.setPhone(phone);
		userDTO.setCars(cars);
		userDTO.setCreatedAt(createdAt);
		userDTO.setLastLogin(lastLogin);
		return userDTO;
	}

}
