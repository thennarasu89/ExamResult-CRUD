package com.project.Exam_result.Controller;

import java.awt.im.InputContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exam_result.Service.ResultService;
import com.project.Exam_result.dto.Input;
import com.project.Exam_result.dto.Response;

import jakarta.validation.Valid;

import com.project.Exam_result.dto.Request;



@RestController
@RequestMapping("/result")
public class ResultController {
	@Autowired
	private ResultService resultService;
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
	public ResponseEntity<String> saveResult(@Valid @RequestBody Input input){
		resultService.saveResult(input);
		return ResponseEntity.ok("Result saved ");
		
	}
	@PostMapping("/fetch")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMIN')")
	public ResponseEntity<Response> fetchResult(@RequestBody Request dto){
		Response response = resultService.fetchResult(dto);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
	public ResponseEntity<String> updateResult(@RequestBody Input dto){
		resultService.updateResult(dto);
        return ResponseEntity.ok("Result updated successfully");
	}
	
    @DeleteMapping("/delete/{regno}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteResult(@PathVariable Long regno) {
        resultService.deleteResult(regno);
        return ResponseEntity.ok("Result deleted successfully");
    }


	
}
