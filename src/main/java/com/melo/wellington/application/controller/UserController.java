package com.melo.wellington.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.resource.UserResource;

@RestController
public class UserController implements UserResource{

	@Override
	public ResponseEntity<List<UserDTO>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDTO> save(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDTO> getUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDTO> updateUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
