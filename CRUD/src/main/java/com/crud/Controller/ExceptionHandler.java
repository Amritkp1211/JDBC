package com.crud.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity playerNotFoundException(PlayerNotFoundException p){
		return  new ResponseEntity(p.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
    
	
}
