package com.example.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course_Entity {

			
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		
		@Column(name ="course_name")
		private String courseName;
		
				
		public Course_Entity() {
			
			
		}
			
		
		public Course_Entity(String courseName) {
			super();
			this.courseName = courseName;
			
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String firstName) {
			this.courseName = firstName;
		}
		
			
		
		
	}


