package com.panos.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.panos.demo.domain.Student;
import com.panos.demo.exception.StudentNotFoundException;
import com.panos.demo.repository.StudentRepository;


@RestController
public class StudentController {

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> retrieveAllStudents() {
		
		logger.debug("Requested all students");
		
		return studentRepository.findAll();
	}

	@GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Student> retrieveStudent(@PathVariable long id) {
		
		logger.debug("Requested student with id = " + id );
		
		Optional<Student> student = studentRepository.findById(id);

		if (!student.isPresent())
			throw new StudentNotFoundException("Not found student with id: " + id);

		Resource<Student> resource = new Resource<Student>(student.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());
		resource.add(linkTo.withRel("all-students"));
		
		logger.info(student.toString());
		
		return resource;
	}


	@PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		logger.info("Student added with id = " + savedStudent.getId());
		
		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteStudent(@PathVariable long id) {
		
		studentRepository.deleteById(id);
		
		logger.info("Student deleted - id =[" + id + "]");
	}
	
	@PutMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		studentRepository.save(student);
		
		logger.info("Student updated - id =[" + id + "]");

		return ResponseEntity.noContent().build();
		
	}
}
