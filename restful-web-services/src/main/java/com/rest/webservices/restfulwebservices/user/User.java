package com.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ApiModel(description = "All details about the user")
public class User {

	User() {}
	
//	User(String name, Date dob) {
//		this.name = name;
//		this.dob = dob;
//	}
	
//	@Min(1)
	@Id // Specifies the primary key
	@GeneratedValue // Generated incrementally
	private Integer id;
	
	@Size(min = 4, message = "Name length should have atleast 4 characters")
	@ApiModelProperty(notes = "Name length should have atleast 4 charecters")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Date of birth should be in the past")
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
}
