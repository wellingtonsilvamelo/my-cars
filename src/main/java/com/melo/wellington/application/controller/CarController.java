package com.melo.wellington.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.resource.CarResource;

@RestController
public class CarController implements CarResource{

	@Override
	public ResponseEntity<List<CarDTO>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<CarDTO> save(CarDTO carDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<CarDTO> getCar(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteCar(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<CarDTO> updateCar(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
