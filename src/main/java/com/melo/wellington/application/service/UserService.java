package com.melo.wellington.application.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.exception.ApiException;
import com.melo.wellington.application.repository.UserRepository;

@Service(value = "userService")
@Transactional(rollbackOn=Exception.class)
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarService carService;
		
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User saveUser(User user){
		
		Optional<User> userExists = getUserByLogin(user.getLogin());
		
		if(userExists.isPresent()) {
			throw new ApiException("Login already exists!");
		}else {
			Optional<User> emailExists = getFirstUserByEmail(user.getEmail());
			
			if(emailExists.isPresent()) {
				throw new ApiException("Email already exists!");
			}			
			user =  userRepository.save(user);
			
			for(Car car : user.getCars()) {
				car.setUser(user);
			}
			
			user.setCars(carService.saveAllCar(user.getCars()));
		}
		
		return user;
	}

	
	public User getUser(Long id){
		Optional<User> result = userRepository.findById(id); 
		if(result.isPresent()) {
			return result.get();
		}
		throw new ApiException("User not found");		
	}
	
	public Optional<User> getUserByLogin(String login){
		Optional<User> result = userRepository.findByLogin(login); 
		if(result.isPresent()) {
			return result;
		}
		return Optional.empty();		
	}
	
	public Optional<User> getFirstUserByEmail(String email){
		Optional<User> result = userRepository.findFirstByEmail(email); 
		if(result.isPresent()) {
			return result;
		}
		return Optional.empty();		
	}
	
	public void removeUser(Long id){
		userRepository.deleteById(id);
	}

	
	public User updateUser(User user){
		
		Optional<User> exists = userRepository.findById(user.getId());
		
		if(exists.isPresent()) {
			User aux = exists.get();
			
			Optional<User> loginExists = getUserByLogin(user.getLogin());
			
			Optional<User> emailExists = getFirstUserByEmail(user.getEmail());
			
			if(emailExists.isPresent()) {
				throw new ApiException("Email already exists!");
			}
			
			if(loginExists.isPresent()) {
				throw new ApiException("Login already exists!");
			}
			
			aux = UserBuilder.create()
					.id(aux.getId())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.birthday(user.getBirthday())
					.createdAt(user.getCreatedAt())
					.email(user.getEmail())
					.lastLogin(user.getLastLogin())
					.login(user.getLogin())
					.phone(user.getPhone())
					.build();
			
			return userRepository.save(aux);
		}
		
		throw new ApiException("User not found!");
	}


}
