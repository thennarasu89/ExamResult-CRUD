package com.project.Exam_result.integration;

import java.awt.print.Printable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.descriptor.TestTemplateTestDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.mysql.cj.xdevapi.AddResult;
import com.project.Exam_result.Entity.Result;
import com.project.Exam_result.Entity.Student;
import com.project.Exam_result.Repo.ResultRepo;
import com.project.Exam_result.Repo.StudentRepo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.project.Exam_result.dto.Input;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@Transactional
public class ResultControllerIntegrationTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ResultRepo resultRepo;
	
	
	private Student testStudent;
	
	
	@BeforeEach
	public void setup() {;
		resultRepo.deleteAll();
		studentRepo.deleteAll();
		testStudent = new Student();
		testStudent.setRegno(200121);
		testStudent.setName("maniii");
		testStudent.setDob(LocalDate.of(2002, 11, 15));
		studentRepo.saveAndFlush(testStudent);
		
	}
	
	@Test 
	public void AddResult() throws Exception {
		Input result = new Input();
		result.setRegno(testStudent.getRegno());
		result.setMark1(90);
		result.setMark2(100);
		result.setMark3(80);
		result.setMark4(75);
		result.setMark5(95);
		result.setMark6(90);
//		result.setTotal(500);
//		result.setResult("PASS");
		
		mockMvc.perform(post("/result/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(result)))
                .andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void Fetchreults() throws Exception{
		Student managedStudent = studentRepo.findById(testStudent.getRegno()).orElseThrow();
		Result result = new Result();
		result.setRegno(managedStudent.getRegno());
		result.setMark1(90);
		result.setMark2(100);
		result.setMark3(80);
		result.setMark4(75);
		result.setMark5(95);
		result.setMark6(90);
		result.setTotal(530);
		result.setResult("PASS");
		result.setStudent(managedStudent);
		resultRepo.saveAndFlush(result);
		
		Map<String, Object> request = new HashMap<>();
		request.put("regno", testStudent.getRegno());
		request.put("dob", testStudent.getDob());
		
		mockMvc.perform(post("/result/fetch")
				.contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(request)))
		 .andDo(mvresult -> {
             System.out.println("❌ Response Status: " + mvresult.getResponse().getStatus());
             System.out.println("❌ Response Body: " + mvresult.getResponse().getContentAsString());
         })
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.total").value(530));
		
	}
}
