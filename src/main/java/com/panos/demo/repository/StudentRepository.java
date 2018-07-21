package com.panos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panos.demo.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
