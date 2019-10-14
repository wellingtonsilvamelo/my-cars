package com.melo.wellington.application.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.request.LoginRequest;
import com.melo.wellington.application.resource.LoginResource;
import com.melo.wellington.application.response.LoginResponse;

@RestController
public class LoginController implements LoginResource{

	@Override
	public ResponseEntity<LoginResponse> signin(LoginRequest loginRequest, String authorization,
			HttpServletResponse response) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDTO> me(String authorization) {
		
		
		return null;
	}

}
