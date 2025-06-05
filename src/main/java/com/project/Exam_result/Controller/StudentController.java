package com.project.Exam_result.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exam_result.Entity.Student;
import com.project.Exam_result.Repo.StudentRepo;

@RestController
@RequestMapping("/add")
public class StudentController {
	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping
	public ResponseEntity<String> saveStudent(@RequestBody Student student){
		studentRepo.save(student);
		System.out.println("Regno from input: " + student.getRegno());

		return ResponseEntity.ok("student saved successfully");
	}

}
