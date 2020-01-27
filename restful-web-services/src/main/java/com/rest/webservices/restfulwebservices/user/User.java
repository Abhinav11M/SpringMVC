package com.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	User() {}
	
//	User(String name, Date dob) {
//		this.name = name;
//		this.dob = dob;
//	}
	
	@Min(1)
	private Integer id;
	
	@Size(min = 4, message = "Name length should have atleast 4 characters")
	private String name;
	
	@Past
	private Date dob;
}
