package com.melo.wellington.application.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.response.LoginResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User", value = "Resource in charge to realize user operations.")
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public interface UserResource {
	
	@ApiOperation(value = "Get all users of the system.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<List<UserDTO>> getAll();
	
	@ApiOperation(value = "Registry a news user system")
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO);
	
	@ApiOperation(value = "Get a specially user by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId);
	
	@ApiOperation(value = "Delete a specially user by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<?> deleteUser(@PathVariable("id") Long userId);
	
	@ApiOperation(value = "Update a specially user by id")
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId);	
	
}
