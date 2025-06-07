package com.project.Exam_result.Service.impl;
import com.project.Exam_result.Service.ResultService;

import com.project.Exam_result.dto.Input;

import jakarta.persistence.Version;

import com.project.Exam_result.dto.*;
import com.project.Exam_result.Entity.*;
import com.project.Exam_result.Exception.ResourceNotFound;
import com.project.Exam_result.Repo.*;

import java.util.Optional;
import java.util.function.LongFunction;

import org.hibernate.engine.spi.ExecuteUpdateResultCheckStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ResultServiceImpl implements ResultService{
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ResultRepo resultRepo;
	
	@Override
	public void saveResult(Input input) {
		Optional<Result> existingResultOpt = resultRepo.findById(input.getRegno());
		if(existingResultOpt.isPresent()) {
			Result existingResult = existingResultOpt.get();
			existingResult.setMark1(input.getMark1());
            existingResult.setMark2(input.getMark2());
            existingResult.setMark3(input.getMark3());
            existingResult.setMark4(input.getMark4());
            existingResult.setMark5(input.getMark5());
            existingResult.setMark6(input.getMark6());
		
            int total = input.getMark1() + input.getMark2() + input.getMark3() + input.getMark4() + input.getMark5() + input.getMark6();
            existingResult.setTotal(total);
            String Result = (input.getMark1() > 35 && 
					input.getMark2() > 35 &&
					input.getMark3() > 35 &&
					input.getMark4() > 35 &&
					input.getMark5() > 35 &&
					input.getMark6() > 35)? "PASS" : "FAIL";
			existingResult.setResult(Result);
			
			resultRepo.saveAndFlush(existingResult);
			
		}
		
		else {
			
			Student student = studentRepo.findById(input.getRegno()).orElseThrow(() -> new ResourceNotFound("Student not found with Register number:" + input.getRegno()));
		Result examResult = new Result();
		examResult.setStudent(student);
		examResult.setMark1(input.getMark1());
		examResult.setMark2(input.getMark2());
		examResult.setMark3(input.getMark3());
		examResult.setMark4(input.getMark4());
		examResult.setMark5(input.getMark5());
		examResult.setMark6(input.getMark6());
        int total = input.getMark1() + input.getMark2() + input.getMark3() + input.getMark4() + input.getMark5() + input.getMark6();

		examResult.setTotal(total);
		
		String Result = (input.getMark1() > 35 && 
				input.getMark2() > 35 &&
				input.getMark3() > 35 &&
				input.getMark4() > 35 &&
				input.getMark5() > 35 &&
				input.getMark6() > 35)? "PASS" : "FAIL";
		examResult.setResult(Result);
		
		
		resultRepo.save(examResult);
		}
	
}
	@Override
	public Response fetchResult(Request dto) {
		Student student = studentRepo.findById(dto.getRegno()).orElseThrow(() -> new ResourceNotFound("Student not found with Register number:" + dto.getRegno()));
		
		
		if(!student.getDob().equals(dto.getDob())) {
			throw new ResourceNotFound("Enter Correct DOB for Register number:" + dto.getRegno());
		}
		Result result = resultRepo.findById(dto.getRegno()).orElseThrow(() -> new ResourceNotFound("Student not found with Register number:" + dto.getRegno()));
		
		Response response = new Response();
		response.setName(student.getName());
		response.setMark1(result.getMark1());
		response.setMark2(result.getMark2());
		response.setMark3(result.getMark3());
		response.setMark4(result.getMark4());
		response.setMark5(result.getMark5());
		response.setMark6(result.getMark6());
		response.setResult(result.getResult());
		response.setTotal(result.getTotal());
		
		return response;
	}
	
	@Override
	public void updateResult(Input dto) {
		Result result = resultRepo.findById(dto.getRegno()).orElseThrow(() -> new ResourceNotFound("Result Data not found with Register number:" + dto.getRegno()));
		
		result.setMark1(dto.getMark1());
	    result.setMark2(dto.getMark2());
	    result.setMark3(dto.getMark3());
	    result.setMark4(dto.getMark4());
	    result.setMark5(dto.getMark5());
	    result.setMark6(dto.getMark6());
	    
	    int total = dto.getMark1() + dto.getMark2() + dto.getMark3() + dto.getMark4() + dto.getMark5() + dto.getMark6();
	    result.setTotal(total);
	    
	    String status = (dto.getMark1() >= 35 && dto.getMark2() >= 35 &&
                dto.getMark3() >= 35 && dto.getMark4() >= 35 &&
                dto.getMark5() >= 35 && dto.getMark6() >= 35)
                ? "Pass" : "Fail";
        result.setResult(status);
        resultRepo.save(result);
		
	}
	
	@Override
	public void deleteResult(Long Regno) {
		if(!resultRepo.existsById(Regno)) {
			throw new ResourceNotFound("Result Data  not found with Register number:" + Regno);
		}
		
		resultRepo.deleteById(Regno);
	}
}
