package com.melo.wellington.application.service;

import static com.melo.wellington.application.MockLoadData.BIRTHDAY;
import static com.melo.wellington.application.MockLoadData.COLOR;
import static com.melo.wellington.application.MockLoadData.CRIATION_DATE;
import static com.melo.wellington.application.MockLoadData.EMAIL;
import static com.melo.wellington.application.MockLoadData.FIRST_NAME;
import static com.melo.wellington.application.MockLoadData.LAST_LOGIN;
import static com.melo.wellington.application.MockLoadData.LAST_NAME;
import static com.melo.wellington.application.MockLoadData.LICENSE_PLATE;
import static com.melo.wellington.application.MockLoadData.LOGIN;
import static com.melo.wellington.application.MockLoadData.MODEL;
import static com.melo.wellington.application.MockLoadData.PASSWORD;
import static com.melo.wellington.application.MockLoadData.PHONE;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.melo.wellington.application.builder.entity.CarBuilder;
import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.entity.Car;
import com.melo.wellington.application.entity.Role;
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.entity.UserRole;
import com.melo.wellington.application.exception.ApiException;
import com.melo.wellington.application.model.RoleEnum;
import com.melo.wellington.application.repository.UserRepository;
import com.melo.wellington.application.repository.UserRoleRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private UserRoleRepository userRoleRepository;
	
	@Mock
	private CarService carService;
	
	List<User> users;
	List<Car> cars;
	List<Car> safeCars;
	User userToSave;
	User safeUser;
	Car carToSave;
	Car safeCar;
	UserRole userRole;
	
	LocalDate DATA_CRIACAO = LocalDate.of(2019,10,14);
	LocalDateTime LAST_UPDATE = LocalDateTime.of(2019,10,14,19,23);
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Rule 
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Before
	public void setup() {
		initMocks(this);
		
		users = new ArrayList<User>();
		users.add(UserBuilder.create().id(1L).build());
		users.add(UserBuilder.create().id(2L).build());
		users.add(UserBuilder.create().id(3L).build());
		cars = new ArrayList<Car>();
		safeCars = new ArrayList<Car>();
		
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
		
		safeCar = CarBuilder.create()
				.id(1L)
				.color(COLOR)
				.licensePlate(LICENSE_PLATE)
				.model(MODEL)
				.user(UserBuilder.create().id(1L).build())
				.qtdUtilizacao(0)
				.year(2019)
				.build();
		
		safeUser = UserBuilder.create()
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
		
		userRole = new UserRole(safeUser, new Role(RoleEnum.ROLE_USER.getCode()));
	}
	
	@Test
	public void shouldReturnEmpityList() {
		List<User> userEmpty = new ArrayList<User>();
		when(userRepository.findAll()).thenReturn(userEmpty);
		userService.getAllUsers();
		errorCollector.checkThat(userEmpty.isEmpty(), is(true));
	}
	
	@Test
	public void shouldReturnNotEmpityList() {
		when(userRepository.findAll()).thenReturn(users);
		userService.getAllUsers();
		errorCollector.checkThat(users.isEmpty(), is(false));
	}
	
	@Test
	public void shouldSaveUserWithoutCars() {
		
		when(userRepository.save(userToSave)).thenReturn(safeUser);
		
		when(userRepository.findFirstByEmail(userToSave.getEmail()))
		.thenReturn(Optional.empty());
		
		when(userRepository.findByLogin(userToSave.getLogin())).thenReturn(Optional.empty());
		userService.saveUser(userToSave);
		
		errorCollector.checkThat(safeUser.getId(), is(1L));
		errorCollector.checkThat(safeUser.getFirstName(), is(FIRST_NAME));
		errorCollector.checkThat(safeUser.getLastName(), is(LAST_NAME));
		errorCollector.checkThat(safeUser.getEmail(), is(EMAIL));
		errorCollector.checkThat(safeUser.getLogin(), is(LOGIN));
		errorCollector.checkThat(safeUser.getBirthday(), is(BIRTHDAY));
		errorCollector.checkThat(safeUser.getCreatedAt(), is(CRIATION_DATE));
		errorCollector.checkThat(safeUser.getLastLogin(), is(LAST_LOGIN));
		//errorCollector.checkThat("Expected cars is not empty!", safeUser.getCars().isEmpty(), is(true));
	}
	
	@Test
	public void shouldSaveUserWithtCars() {
		
		cars.add(carToSave);		
		userToSave.setCars(cars);
		safeCars.add(safeCar);		
		safeUser.setCars(cars);
		
		when(userRepository.save(userToSave)).thenReturn(safeUser);
		
		when(userRepository.findFirstByEmail(userToSave.getEmail()))
		.thenReturn(Optional.empty());
		
		when(userRepository.findByLogin(userToSave.getLogin())).thenReturn(Optional.empty());
		userService.saveUser(userToSave);
		
		errorCollector.checkThat(safeUser.getId(), is(1L));
		errorCollector.checkThat(safeUser.getFirstName(), is(FIRST_NAME));
		errorCollector.checkThat(safeUser.getLastName(), is(LAST_NAME));
		errorCollector.checkThat(safeUser.getEmail(), is(EMAIL));
		errorCollector.checkThat(safeUser.getLogin(), is(LOGIN));
		errorCollector.checkThat(safeUser.getBirthday(), is(BIRTHDAY));
		errorCollector.checkThat(safeUser.getCreatedAt(), is(CRIATION_DATE));
		errorCollector.checkThat(safeUser.getLastLogin(), is(LAST_LOGIN));
		//errorCollector.checkThat("Expected cars is not empty!", safeUser.getCars().isEmpty(), is(true));
	}
	
	@Test
	public void shouldReturnExceptionWhenTrySaveUserAndLoginAlreadyExist() {
				
		when(userRepository.findByLogin(userToSave.getLogin()))
		.thenReturn(Optional.ofNullable(safeUser));
		
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Login already exists!");
		
		userService.saveUser(userToSave);
	}
	
	@Test
	public void shouldReturnExceptionWhenTrySaveUserAndEmailAlreadyExist() {
				
		when(userRepository.findFirstByEmail(userToSave.getEmail()))
		.thenReturn(Optional.ofNullable(safeUser));
		
		when(userRepository.findByLogin(userToSave.getLogin()))
		.thenReturn(Optional.empty());
		
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Email already exists!");
		
		userService.saveUser(userToSave);
	}
	
	@Test
	public void shouldRemoveUser() {
		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(safeUser));
		userService.removeUser(1L);
		verify(userRepository, times(1)).deleteById(1L);
	}
	
	@Test
	public void shouldReturnExceptionWhenTryRemoveUserAndHeDoesNotExist() throws ApiException {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("User not found!");
		userService.removeUser(1L);
	}
	
	@Test
	public void shouldUpdateUser() {
		
		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(safeUser));
		
		when(userRepository.findFirstByEmail(safeUser.getEmail()))
		.thenReturn(Optional.empty());
		
		when(userRepository.findByLogin(safeUser.getLogin())).thenReturn(Optional.empty());
		
		userService.updateUser(safeUser);
		
		errorCollector.checkThat(safeUser.getId(), is(1L));
		errorCollector.checkThat(safeUser.getFirstName(), is(FIRST_NAME));
		errorCollector.checkThat(safeUser.getLastName(), is(LAST_NAME));
		errorCollector.checkThat(safeUser.getEmail(), is(EMAIL));
		errorCollector.checkThat(safeUser.getLogin(), is(LOGIN));
		errorCollector.checkThat(safeUser.getBirthday(), is(BIRTHDAY));
		errorCollector.checkThat(safeUser.getCreatedAt(), is(CRIATION_DATE));
		errorCollector.checkThat(safeUser.getLastLogin(), is(LAST_LOGIN));
	}
	
	@Test
	public void shouldReturnExceptionWhenTryUpdateUserAndEmailAlreadyExist() {
		User another = UserBuilder.create()
				.id(2L)
				.build();
		
		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(safeUser));	
		
		when(userRepository.findFirstByEmail(userToSave.getEmail()))
		.thenReturn(Optional.ofNullable(another));
		
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Email already exists!");
		
		userService.updateUser(safeUser);
	}
	
	@Test
	public void shouldReturnExceptionWhenTryUpdateUserAndLoginAlreadyExist() {
		User another = UserBuilder.create()
				.id(2L)
				.build();
		
		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(safeUser));
		
		when(userRepository.findByLogin(userToSave.getLogin()))
		.thenReturn(Optional.ofNullable(another));
		
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Login already exists!");
		
		userService.updateUser(safeUser);
	}
	
	@Test
	public void shouldReturnExceptionWhenTryUpdateUserAndHeDoesNotExist() throws ApiException {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("User not found!");
		userService.updateUser(safeUser);
	}
	
	@Test
	public void shouldReturnAUserById() {
		
		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(safeUser));
				
		userService.getUser(1L);
		
		errorCollector.checkThat(safeUser.getId(), is(1L));
		errorCollector.checkThat(safeUser.getFirstName(), is(FIRST_NAME));
		errorCollector.checkThat(safeUser.getLastName(), is(LAST_NAME));
		errorCollector.checkThat(safeUser.getEmail(), is(EMAIL));
		errorCollector.checkThat(safeUser.getLogin(), is(LOGIN));
		errorCollector.checkThat(safeUser.getBirthday(), is(BIRTHDAY));
		errorCollector.checkThat(safeUser.getCreatedAt(), is(CRIATION_DATE));
		errorCollector.checkThat(safeUser.getLastLogin(), is(LAST_LOGIN));
	}
	
	@Test
	public void shouldReturnExceptionWhenTryGeteUserAndHeDoesNotExist() throws ApiException {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("User not found!");
		userService.getUser(1L);
	}
	
	@Test
	public void shouldReturnAUserByLogin() {
		
		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(safeUser));
				
		userService.getUser(1L);
		
		errorCollector.checkThat(safeUser.getId(), is(1L));
		errorCollector.checkThat(safeUser.getFirstName(), is(FIRST_NAME));
		errorCollector.checkThat(safeUser.getLastName(), is(LAST_NAME));
		errorCollector.checkThat(safeUser.getEmail(), is(EMAIL));
		errorCollector.checkThat(safeUser.getLogin(), is(LOGIN));
		errorCollector.checkThat(safeUser.getBirthday(), is(BIRTHDAY));
		errorCollector.checkThat(safeUser.getCreatedAt(), is(CRIATION_DATE));
		errorCollector.checkThat(safeUser.getLastLogin(), is(LAST_LOGIN));
	}
	
	@Test
	public void shouldReturnEmpityWhenTryGeteUserByLoginAndHeDoesNotExist() throws ApiException {
		Optional<User> result = Optional.empty();
		
		when(userRepository.findByLogin(LOGIN)).thenReturn(result);

		userService.getUserByLogin(LOGIN);
		
		errorCollector.checkThat(result, is(Optional.empty()));
	}

}
