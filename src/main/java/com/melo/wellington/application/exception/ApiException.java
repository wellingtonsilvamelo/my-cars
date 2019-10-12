package com.melo.wellington.application.exception;

public class ApiException extends RuntimeException{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 4376437720874140662L;

	public ApiException(String s) {
		super(s);
	}

	public ApiException(String s, Throwable throwable) {
		super(s, throwable);
	}

}
