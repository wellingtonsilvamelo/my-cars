package com.melo.wellington.application;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.melo.wellington.application.builder.entity.UserBuilder;
import com.melo.wellington.application.entity.User;

public class MockLoadData {
	
	/*************************				USER DATA			***************************/
	public static final String FIRST_NAME = "Wellington";
	public static final String LAST_NAME = "Melo";
	public static final String EMAIL = "wellingtonsilvamelo@gmail.com";
	public static final String LOGIN = "wellingtonsilvamelo";
	public static final LocalDate BIRTHDAY = LocalDate.of(1986,6,6);
	public static final String PASSWORD = "12345678";
	public static final String PHONE = "81999999999";
	public static final LocalDate CRIATION_DATE = LocalDate.of(2019,10,14);
	public static final LocalDateTime LAST_LOGIN= LocalDateTime.of(2019,10,14,19,23);
	
	/*************************				CAR DATA			***************************/
	public static final String COLOR = "Metalic White";
	public static final String LICENSE_PLATE = "TOM4236";
	public static final String MODEL = "Fiat Argo Drive 1.3 Firefly";
	public static final User USER = UserBuilder.create().id(1L).build();
	public static final Integer QTD_UTILIZACAO = 0;
	public static final Integer YEAR = 2019;
	
}
