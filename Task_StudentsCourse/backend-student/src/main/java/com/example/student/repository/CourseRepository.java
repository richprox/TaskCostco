package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Course_Entity;

@Repository
public interface CourseRepository extends JpaRepository<Course_Entity, Long>{
	
	
	
}
