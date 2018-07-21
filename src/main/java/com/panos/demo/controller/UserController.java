package com.panos.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panos.demo.domain.User;

@RestController
public class UserController {

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@RequestMapping("/")
    public String index() {
		
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping("/users")
	public ResponseEntity<User> GetAllUsers(){
		
		 logger.info("Request all users");
		 
		 final User user = new User((long) 1, "Panos", "Stasinopoulos", new GregorianCalendar(), new Date());
		
		 return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

    @RequestMapping(value="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<User> getUserById(@PathVariable(required = true) final Long id) {

        logger.info("Request user - id =[" + id + "]");

        final User user = new User(id, "Panos", "Stasinopoulos", new GregorianCalendar(), new Date());

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).GetAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		logger.info(String.format("id=[%s] firstname=[%s] surname=[%s]", user.getId(), user.getFirstname(), user.getSurname()));
		
        return resource;
    }
}
