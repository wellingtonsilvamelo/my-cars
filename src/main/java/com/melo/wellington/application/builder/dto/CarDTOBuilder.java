package com.melo.wellington.application.builder.dto;

import com.melo.wellington.application.dto.CarDTO;

public class CarDTOBuilder {
	
	private Long id;
	private Long userId;
	private int year;
	private String licensePlate;
	private String model;
	private String color;
	
	public static CarDTOBuilder create() {
		return new CarDTOBuilder();
	}
	
	public CarDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}
	public CarDTOBuilder userId(Long userId) {
		this.userId = userId;
		return this;
	}
	public CarDTOBuilder year(int year) {
		this.year = year;
		return this;
	}
	public CarDTOBuilder licensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
		return this;
	}
	public CarDTOBuilder model(String model) {
		this.model = model;
		return this;
	}
	public CarDTOBuilder color(String color) {
		this.color = color;
		return this;
	}
	
	public CarDTO build() {
		CarDTO carDTO = new CarDTO();
		carDTO.setId(id);
		carDTO.setUserId(userId);
		carDTO.setLicensePlate(licensePlate);
		carDTO.setModel(model);
		carDTO.setYear(year);
		carDTO.setColor(color);
		return carDTO;
	}

}
