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

import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.response.LoginResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Car", value = "Resource in charge to realize car operations.")
@RequestMapping("/api/cars")
@CrossOrigin(origins = "http://localhost:4200")
public interface CarResource {
	
	@ApiOperation(value = "Get all the user logged's cars")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<List<CarDTO>> getAll();
	
	@ApiOperation(value = "Registry a new car to the user logged")
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<CarDTO> save(@Valid @RequestBody CarDTO carDTO);
	
	@ApiOperation(value = "Get a car from user logged in by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<CarDTO> getCar(@PathVariable("id") Long userId);
	
	@ApiOperation(value = "Delete a car from user logged in by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<?> deleteCar(@PathVariable("id") Long userId);
	
	@ApiOperation(value = "Update a car from user logged in by id")
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<CarDTO> updateCar(@PathVariable("id") Long userId);	

}
