package com.melo.wellington.application.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.melo.wellington.application.MockLoadData.*;
import com.melo.wellington.application.builder.entity.CarBuilder;
import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CarService carService;
	
	List<User> users;
	List<Car> cars;
	User userToSave;
	User userSaved;
	Car carToSave;
	Car carSaved;
	
	LocalDate DATA_CRIACAO = LocalDate.of(2019,10,14);
	LocalDateTime LAST_UPDATE = LocalDateTime.of(2019,10,14,19,23);
	
	@Before
	public void setup() {
		initMocks(this);
		
		users = new ArrayList<User>();
		users.add(UserBuilder.create().id(1L).build());
		users.add(UserBuilder.create().id(2L).build());
		users.add(UserBuilder.create().id(3L).build());
		
		userToSave = UserBuilder.create()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.email(EMAIL)
				.login(LOGIN)
				.birthday(BIRTHDAY)
				.password(PASSWORD)
				.phone(PHONE)
				.build();
		
		carToSave = CarBuilder.create()
				.color(COLOR)
				.licensePlate(LICENSE_PLATE)
				.model(MODEL)
				.user(UserBuilder.create().id(1L).build())
				.qtdUtilizacao(0)
				.year(2019)
				.build();
		
		carSaved = CarBuilder.create()
				.id(1L)
				.color(COLOR)
				.licensePlate(LICENSE_PLATE)
				.model(MODEL)
				.user(UserBuilder.create().id(1L).build())
				.qtdUtilizacao(0)
				.year(2019)
				.build();
		
		userSaved = UserBuilder.create()
				.id(1L)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.email(EMAIL)
				.login(LOGIN)
				.birthday(BIRTHDAY)
				.password(PASSWORD)
				.phone(PHONE)
				.lastLogin(LAST_LOGIN)
				.createdAt(CRIATION_DATE)
				.cars(new ArrayList<CarDTO>())
				.build();
	}
	
	@Test
	public void whenUserListIsEmpty() {
		List<User> userEmpty = new ArrayList<User>();
		when(userService.getAllUsers()).thenReturn(userEmpty);
		userService.getAllUsers();
		assertThat(userEmpty.isEmpty(), is(true));
	}
	
	@Test
	public void whenUserListIsNotEmpty() {
		when(userService.getAllUsers()).thenReturn(users);
		userService.getAllUsers();
		assertThat(users.isEmpty(), is(false));
	}
	
	@Test
	public void whenSaveUser() {
		
		when(userRepository.save(userToSave)).thenReturn(userSaved);
		
		when(userRepository.findFirstByEmail(userToSave.getEmail()))
		.thenReturn(Optional.empty());
		
		when(userRepository.findByLogin(userToSave.getLogin())).thenReturn(Optional.empty());
		userService.saveUser(userToSave);
		
		assertThat(userSaved.getId(), is(1L));
		assertThat(userSaved.getFirstName(), is(FIRST_NAME));
		assertThat(userSaved.getLastName(), is(LAST_NAME));
		assertThat(userSaved.getEmail(), is(EMAIL));
		assertThat(userSaved.getLogin(), is(LOGIN));
		assertThat(userSaved.getBirthday(), is(BIRTHDAY));
		assertThat(userSaved.getCreatedAt(), is(CRIATION_DATE));
		assertThat(userSaved.getLastLogin(), is(LAST_LOGIN));
		assertThat("Expected cars is not empty!", userSaved.getCars().isEmpty(), is(true));
	}

}
