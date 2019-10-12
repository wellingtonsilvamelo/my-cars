package com.melo.wellington.application.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tbg_user", uniqueConstraints={@UniqueConstraint(columnNames ={"user_email","username"})})
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long id;
	
	@Column(name="user_firstName", length=50, nullable=false)
	private String firstName;
	
	@Column(name="user_lastname", length=50, nullable=false)
	private String lastName;
	
	@Column(name="user_email", length=100, nullable=false)
	private String email;
	
	@Column(name="user_birthday", nullable=false)
	private LocalDate birthday;
	
	@Column(name="username", length=20, nullable=false)
	private String login;
	
	@Column(name="password", length=100, nullable=false)
	private String password;
	
	@Column(name="user_phone", length=12, nullable=true)
	private String phone;
	
	@Column(name="user_date_creation", length=12, nullable=true)
	private LocalDate createdAt;
	
	@Column(name="user_last_login", length=12, nullable=true)
	private LocalDateTime lastLogin;
	
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
