package com.melo.wellington.application.request;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

	@NotEmpty(message = "Invalid login or password")
	private String username;
	
	@NotEmpty(message = "Invalid login or password")
	private String password;
	
	@NotEmpty(message = "Invalid login or password")
	private String grant_type;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	
}
