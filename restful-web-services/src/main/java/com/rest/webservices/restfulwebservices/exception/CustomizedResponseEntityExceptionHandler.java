package com.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
//@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest req) {
		System.out.println("testing");
		ExceptionResponse exceptionRes = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(exceptionRes, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {
		ExceptionResponse exceptionRes = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(exceptionRes, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Object> handleAllRuntimeException(Exception ex, WebRequest req) {
		ExceptionResponse exceptionRes = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(exceptionRes, HttpStatus.BAD_GATEWAY);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionRes = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionRes, HttpStatus.FORBIDDEN);
	}
	
}
