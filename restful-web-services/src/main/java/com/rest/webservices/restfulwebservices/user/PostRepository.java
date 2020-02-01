package com.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for User <Entity, PrimaryKey>. Here Id is a primary key which is an integer.
public interface PostRepository extends JpaRepository<Post, Integer>{

}
