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
	@Size(min=7, max=10, message="Invalid fields!")
	private String licensePlate;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=5, max=100, message="Invalid fields!")
	private String model;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=4, max=30, message="Invalid fields!")
	private String color;
	
	private Integer amountUse;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDTO other = (CarDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	public Integer getAmountUse() {
		return amountUse;
	}
	public void setAmountUse(Integer qtdUtilizacao) {
		this.amountUse = qtdUtilizacao;
	}
	
}
