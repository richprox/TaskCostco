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
import com.example.student.model.Student_Entity;
import com.example.student.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	
	@GetMapping("/student")
	public List<Student_Entity> getAllStudents(){
		return studentRepository.findAll();
	}		
	
	
	@PostMapping("/student")
	public Student_Entity createStudent(@RequestBody Student_Entity Student) {
		return studentRepository.save(Student);
	}
	
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student_Entity> getStudentById(@PathVariable Long id) {
		Student_Entity Student = studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Student not exist with id :" + id));
		return ResponseEntity.ok(Student);
	}
	
	
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student_Entity> updateStudent(@PathVariable Long id, @RequestBody Student_Entity StudentDetails){
		Student_Entity Student = studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Student not exist with id :" + id));
		
		Student.setFirstName(StudentDetails.getFirstName());
		Student.setLastName(StudentDetails.getLastName());
		Student.setEmailId(StudentDetails.getEmailId());
		
		Student_Entity updatedStudent = studentRepository.save(Student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
		Student_Entity Student = studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Student not exist with id :" + id));
		
		studentRepository.delete(Student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
