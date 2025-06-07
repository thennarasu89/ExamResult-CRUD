package com.project.Exam_result.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exam_result.dto.Input;
import com.project.Exam_result.Entity.Student;
import com.project.Exam_result.Repo.StudentRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/add")
	public ResponseEntity<String> createStudent(@Valid @RequestBody Student student){
		if(studentRepo.existsById(student.getRegno())) {
			return ResponseEntity.status(409).body("Register Number Already exist");
		}
		studentRepo.save(student);
		System.out.println("Regno from input: " + student.getRegno());

		return ResponseEntity.ok("student saved successfully");
	}
	@PutMapping("/update/{regno}")
	public ResponseEntity<String> updateStudent(@PathVariable long regno, @Valid @RequestBody Student updatedStudent){
		if(!studentRepo.existsById(regno)) {
			return ResponseEntity.status(409).body("student with register number" + regno + "not exist");
			
		}
		updatedStudent.setRegno(regno);
		studentRepo.save(updatedStudent);
		return ResponseEntity.ok("sucesss");
	}
}
