package com.melo.wellington.application.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.melo.wellington.application.builder.dto.UserDTOBuilder;
import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.request.LoginRequest;
import com.melo.wellington.application.resource.LoginResource;
import com.melo.wellington.application.response.LoginResponse;
import com.melo.wellington.application.service.CarService;
import com.melo.wellington.application.service.UserService;

@RestController
public class LoginController implements LoginResource{
	
	private AuthenticationManager authenticationManager;
	
	private UserService userService;
	
	private CarService carService;
	
	@Autowired
	public LoginController(AuthenticationManager authenticationManager, UserService userService,
			CarService carService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.carService = carService;
	}

	@Override
	public ResponseEntity<LoginResponse> signin(LoginRequest loginRequest, String authorization,
			HttpServletResponse response) throws UnsupportedEncodingException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return ResponseEntity.ok(new LoginResponse("","",0L)/*tokenService.create(device), jwtTokenFactory.generateExpirationDate(), jwtSecurityProperties.getType())*/);
		
	}

	@Override
	public ResponseEntity<UserDTO> get(Principal principal) {
		
		User user = userService.getUserByLogin(principal.getName()).get();
		List<Car> cars = carService.getAllCarsByUser(user);
		
		UserDTO userDTO = UserDTOBuilder.create()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.birthday(user.getBirthday())
				.createdAt(user.getCreatedAt())
				.email(user.getEmail())
				.lastLogin(user.getLastLogin())
				.login(user.getLogin())
				.phone(user.getPhone())
				.cars(cars)
				.build();	
		
        return ResponseEntity.ok(userDTO);
    }

	@Override
	public ResponseEntity<Boolean> validateToken(String authorization) {
		return ResponseEntity.ok(true);
	}

}
