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
import static com.melo.wellington.application.MockLoadData.QTD_UTILIZACAO;
import static com.melo.wellington.application.MockLoadData.USER;
import static com.melo.wellington.application.MockLoadData.YEAR;
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
import com.melo.wellington.application.entity.User;
import com.melo.wellington.application.exception.ApiException;
import com.melo.wellington.application.repository.CarRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceTest {
	
	@InjectMocks
	private CarService carService;
	
	@Mock
	private CarRepository carRepository;
	
	List<User> users;
	List<Car> cars;
	List<Car> safeCars;
	User userToSave;
	User safeUser;
	Car carToSave;
	Car safeCar;
	
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
	}
	
	@Test
	public void shouldReturnNotEmpityList() {
		safeCars.add(safeCar);
		when(carRepository.findAll()).thenReturn(safeCars);
		carService.getAllCars();
		errorCollector.checkThat(safeCars.isEmpty(), is(false));
	}
	
	@Test
	public void shouldReturnNotEmpityListCarsByUser() {
		safeCars.add(safeCar);
		when(carRepository.findByUser(safeUser)).thenReturn(safeCars);
		carService.getAllCarsByUser(safeUser);
		errorCollector.checkThat(safeCars.isEmpty(), is(false));
	}
	
	@Test
	public void shouldSaveCar() {
		safeCar.setUser(USER);
		
		when(carRepository.save(carToSave)).thenReturn(safeCar);
		
		when(carRepository.findFirstByLicensePlate(carToSave.getLicensePlate()))
		.thenReturn(Optional.empty());
		
		carService.saveCar(carToSave);
		
		errorCollector.checkThat(safeCar.getId(), is(1L));
		errorCollector.checkThat(safeCar.getLicensePlate(), is(LICENSE_PLATE));
		errorCollector.checkThat(safeCar.getModel(), is(MODEL));
		errorCollector.checkThat(safeCar.getColor(), is(COLOR));
		errorCollector.checkThat(safeCar.getQtdUtilizacao(), is(QTD_UTILIZACAO));
		errorCollector.checkThat(safeCar.getYear(), is(YEAR));
		errorCollector.checkThat(safeCar.getUser(), is(USER));
	}
		
	@Test
	public void shouldReturnExceptionWhenTrySaveCarAndPlateAlredyExist() {		
		when(carRepository.findFirstByLicensePlate(carToSave.getLicensePlate()))
		.thenReturn(Optional.ofNullable(safeCar));		
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("License plate already exists!");		
		carService.saveCar(carToSave);
	}
	
	@Test
	public void shouldReturnCarById() {
		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(safeCar));
		carService.getCar(1L);
		
		errorCollector.checkThat(safeCar.getId(), is(1L));
		errorCollector.checkThat(safeCar.getQtdUtilizacao(), is(1));
	}
	
	@Test
	public void shouldReturnExceptionWhenTryGetCarAndItDoesNotExist() {
				
		when(carRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Car not found!");
		carService.getCar(1L);
	}
	
	@Test
	public void shouldRemoveCarById() {		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(safeCar));
		carService.removeCar(1L);
		verify(carRepository, times(1)).deleteById(1L);
	}
	
	@Test
	public void shouldReturnExceptionWhenTryRemoveACarAndItDoesNotExist() {				
		when(carRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Car not found!");
		carService.removeCar(1L);
	}
			
	@Test
	public void shouldUpdateCar() {
		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(safeCar));
		
		when(carRepository.findFirstByLicensePlate(safeCar.getLicensePlate()))
		.thenReturn(Optional.empty());
		
		carService.updateCar(safeCar);
		
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
	public void shouldReturnExceptionWhenTryUpdateACarAndItDoesNotExist() {				
		when(carRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("Car not found!");
		carService.updateCar(safeCar);
	}
	
	@Test
	public void shouldReturnExceptionWhenTryUpdateACarAndPlateAlreadyExist() {
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(safeCar));
		when(carRepository.findFirstByLicensePlate(safeCar.getLicensePlate()))
		.thenReturn(Optional.ofNullable(safeCar));		
		expectedException.expect(ApiException.class);
		expectedException.expectMessage("License plate already exists!");		
		carService.updateCar(safeCar);
	}

}
