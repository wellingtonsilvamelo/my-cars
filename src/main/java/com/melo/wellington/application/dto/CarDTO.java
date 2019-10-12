package com.melo.wellington.application.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CarDTO {

	private Long id;
	
	@NotNull(message = "Missing fields!")
	@Min(value = 1, message = "Missing fields!")
	private Long userId;
	
	@Min(value = 1900, message = "Invalid fields!")
	private int year;
	
	@NotEmpty(message="Missing fields!")
	@Pattern(regexp = "{A-Za-z0-9}*", message="Invalid fields!")
	@Size(min=7, max=10, message="Invalid fields!")
	private String licensePlate;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=5, max=100, message="Invalid fields!")
	private String model;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=4, max=30, message="Invalid fields!")
	private String color;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
