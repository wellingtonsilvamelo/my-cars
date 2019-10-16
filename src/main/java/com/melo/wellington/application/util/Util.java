package com.melo.wellington.application.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

}
