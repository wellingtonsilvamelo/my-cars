package com.melo.wellington.application.builder.entity;

import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;

public class CarBuilder {
	
	private Long id;
	private User user;
	private int year;
	private String licensePlate;
	private String model;
	private String color;
	private Integer qtdUtilizacao;
	
	public static CarBuilder create() {
		return new CarBuilder();
	}
	
	public CarBuilder id(Long id) {
		this.id = id;
		return this;
	}
	public CarBuilder user(User user) {
		this.user = user;
		return this;
	}
	public CarBuilder year(int year) {
		this.year = year;
		return this;
	}
	public CarBuilder licensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
		return this;
	}
	public CarBuilder model(String model) {
		this.model = model;
		return this;
	}
	public CarBuilder color(String color) {
		this.color = color;
		return this;
	}
	public CarBuilder qtdUtilizacao(Integer qtdUtilizacao) {
		this.qtdUtilizacao = qtdUtilizacao;
		return this;
	}
	
	public Car build() {
		Car car = new Car();
		car.setId(id);
		car.setUser(user);
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setColor(color);
		car.setAmountUse(qtdUtilizacao);
		return car;
	}

}
