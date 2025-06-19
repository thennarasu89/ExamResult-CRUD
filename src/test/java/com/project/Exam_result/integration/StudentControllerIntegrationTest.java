package com.project.Exam_result.integration;

import org.springframework.http.MediaType; 

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Exam_result.Entity.Student;
import com.project.Exam_result.Repo.StudentRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc

public class StudentControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void createNewst() throws Exception{
		Student student = new Student();
		student.setRegno(100231);
		student.setName("jhon");
		student.setDob(LocalDate.of(2001, 11, 15));
		
		
		mockMvc.perform(post("/student/add")
				.contentType(MediaType.valueOf("application/json"))
				.content(objectMapper.writeValueAsString(student)))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void ReturnConflict() throws Exception{
		Student student = new Student();
		student.setRegno(100231);
		student.setName("jhondee");
		student.setDob(LocalDate.of(2001, 11, 15));
		
		studentRepo.save(student);
		
		mockMvc.perform(post("/student/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isConflict());
		
	}

}
