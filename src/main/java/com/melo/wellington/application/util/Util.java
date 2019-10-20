package com.melo.wellington.application.util;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.melo.wellington.application.dto.CarDTO;
import com.melo.wellington.application.dto.UserDTO;

public class Util {
	
	/**
	 * Generate a hash using bcrypt.
	 * 
	 * @param password
	 * @return String
	 */
	public static String generateBCrypt(String password) {
		if (StringUtils.isNotBlank(password)) {
			BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
			return bCryptEncoder.encode(password.trim());
		}else{
			return password;
		}

	}

	/**
	 * Verify if a password is valid.
	 * 
	 * @param password
	 * @param encodedPassword
	 * @return boolean
	 */
	public static boolean isPassWordValid(String password, String encodedPassword) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(password, encodedPassword);
	}
	
	
	public static List<UserDTO> comparingUsers(List<UserDTO> list){
		
		list.sort(Comparator.nullsLast(Comparator.comparingInt(u -> ((UserDTO) u).getCars().stream().mapToInt(CarDTO::getAmountUse).sum()).reversed()
				.thenComparingInt(u -> ((UserDTO) u).getCars().size())
				.thenComparing(u -> ((UserDTO) u).getLogin())));
		
		return list;
	}
	
	public static List<CarDTO> comparingCars(List<CarDTO> list){
		
		list.sort(Comparator.nullsLast(Comparator.comparingInt(CarDTO::getAmountUse).reversed()
				.thenComparing(CarDTO::getModel)));
		
		return list;
	}

}
