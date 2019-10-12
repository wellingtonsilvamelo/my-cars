package com.melo.wellington.application.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.melo.wellington.application.response.ResponseError;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource message;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<String> errors;
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex);

        errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ResponseError response = new ResponseError(HttpStatus.BAD_REQUEST.value(), errors.get(0));
        errors = null;
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }
	
	@ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        logError(ex);
        
        ResponseError response = new ResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        logError(ex);
        
        ResponseError response = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
        		message.getMessage("exception.general", null, Locale.getDefault()));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	private void logError(Exception ex) {
        logger.error(ex.getClass().getName(), ex);
    }

}
