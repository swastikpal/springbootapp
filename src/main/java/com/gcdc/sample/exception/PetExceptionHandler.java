package com.gcdc.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * 
 * handler class annotated with {@code RestControllerAdvice} 
 * @author swastikpal
 *
 */
@RestControllerAdvice
public class PetExceptionHandler {
	
	@ExceptionHandler(value = PetException.class)
	public ResponseEntity<Object> handleExceptionHandler(PetException ex) {
		
		if (ex.getMessage().contains("Not found")) {
			return ResponseEntity.notFound().build();
		}
		if (ex.getMessage().contains("Delete")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
