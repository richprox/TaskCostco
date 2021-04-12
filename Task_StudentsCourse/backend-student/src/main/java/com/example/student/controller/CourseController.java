package com.example.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.exception.NotFoundException;
import com.example.student.model.Course_Entity;
import com.example.student.repository.CourseRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	
	@GetMapping("/course")
	public List<Course_Entity> getAllCourses(){
		return courseRepository.findAll();
	}		
	
	
	@PostMapping("/course")
	public Course_Entity createCourse(@RequestBody Course_Entity Course) {
		return courseRepository.save(Course);
	}
	
	
	@GetMapping("/course/{id}")
	public ResponseEntity<Course_Entity> getCourseById(@PathVariable Long id) {
		Course_Entity Course = courseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Course not exist with id :" + id));
		return ResponseEntity.ok(Course);
	}
	
	
	
	@PutMapping("/course/{id}")
	public ResponseEntity<Course_Entity> updateCourse(@PathVariable Long id, @RequestBody Course_Entity CourseDetails){
		Course_Entity Course = courseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Course not exist with id :" + id));
		
		Course.setCourseName(CourseDetails.getCourseName());
		
		
		
		Course_Entity updatedCourse = courseRepository.save(Course);
		return ResponseEntity.ok(updatedCourse);
	}
	
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCourse(@PathVariable Long id){
		Course_Entity Course = courseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Course not exist with id :" + id));
		
		courseRepository.delete(Course);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
