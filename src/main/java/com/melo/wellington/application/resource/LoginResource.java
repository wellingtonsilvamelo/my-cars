package com.melo.wellington.application.resource;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.melo.wellington.application.dto.UserDTO;
import com.melo.wellington.application.request.LoginRequest;
import com.melo.wellington.application.response.LoginResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Login", value = "Resource in charge to realize user login.")
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public interface LoginResource {

	@ApiOperation(value = "Do user login in the system.")
	@PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<LoginResponse> signin(@Valid @RequestBody LoginRequest loginRequest, @RequestHeader("Authorization") String authorization,
			   HttpServletResponse  response) throws UnsupportedEncodingException;
	
	@ApiOperation(value = "Get specially user informations.")
	@GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> get(final Principal principal);
	
	@ApiOperation(value = "Get specially user informations.")
	@GetMapping(value = "/validateToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authorization);
}
