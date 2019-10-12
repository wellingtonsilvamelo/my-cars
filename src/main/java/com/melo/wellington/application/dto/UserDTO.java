package com.melo.wellington.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {
	
	private Long id;
	
	@NotEmpty(message="Missing fields!")
	@Pattern(regexp = "{A-Za-z}*", message="Invalid fields!")
	@Size(min=4, max=50, message="Invalid fields!")
	private String firstName;
	
	@NotEmpty(message="Missing fields!")
	@Pattern(regexp = "{A-Za-z}*", message="Invalid fields!")
	@Size(min=4, max=50, message="Invalid fields!")
	private String lastName;
	
	@NotEmpty(message="Missing fields!")
	@Size(max=50, message="Invalid fields!")
	@Email(message="Invalid fields!")
	private String email;
	
	@NotNull(message="Missing fields!")
	private LocalDate birthday;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=4, max=20, message="Invalid fields!")
	private String login;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=8, max=14, message="Invalid fields!")
	@Pattern(regexp = "{A-Za-z0-9}*", message="Invalid fields!")
	private String password;
	
	@NotEmpty(message="Missing fields!")
	@Size(min=10, max=11, message="Invalid fields!")
	private String phone;
	
	private LocalDate createdAt;
	private LocalDateTime lastLogin;
	private List<CarDTO> cars;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<CarDTO> getCars() {
		return cars;
	}
	public void setCars(List<CarDTO> cars) {
		this.cars = cars;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

}
