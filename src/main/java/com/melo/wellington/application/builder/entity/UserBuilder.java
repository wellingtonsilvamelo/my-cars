package com.melo.wellington.application.builder.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.melo.wellington.application.entity.User;

public class UserBuilder {
	
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
	
	public static UserBuilder create() {
		return new UserBuilder();
	}
	
	public UserBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public UserBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public UserBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public UserBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder birthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}
	
	public UserBuilder login(String login) {
		this.login = login;
		return this;
	}
	
	public UserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public UserBuilder createdAt(LocalDate createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public UserBuilder lastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}
		
	public User build() {
		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setBirthday(birthday);
		user.setLogin(login);
		user.setPassword(password);
		user.setPhone(phone);
		user.setCreatedAt(createdAt);
		user.setLastLogin(lastLogin);
		return user;
	}

}
