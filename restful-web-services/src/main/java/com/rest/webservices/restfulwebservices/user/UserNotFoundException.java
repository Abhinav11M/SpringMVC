package com.rest.webservices.restfulwebservices.user;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

// Return response status as not found whenever this exception is thrown
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
