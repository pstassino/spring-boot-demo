package com.panos.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionResponseHandler {
	
	 @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	    
		 ExceptionResponse exceptionResponse = 
	    		new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	    
		 return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  @ExceptionHandler(StudentNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleStudentNotFoundException(StudentNotFoundException ex, WebRequest request) {
	    
		  ExceptionResponse exceptionResponse = 
	    		new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	    
		  return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(UserNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
	    
		  ExceptionResponse exceptionResponse = 
	    		new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	    
		  return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }


}
