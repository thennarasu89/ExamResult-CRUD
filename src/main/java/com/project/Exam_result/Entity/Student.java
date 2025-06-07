package com.project.Exam_result.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="students")

public class Student {
	@Id
	@NotNull(message="/Register number is must")
	@Min(value=10000,message="/Register number must be greater than 10000")
	private long regno;

	private String Name;
	private LocalDate dob;
	
	public Student() {
		super();
		
	}
	
	public Student(long regno, String name, LocalDate dob) {
		super();
		this.regno = regno;
		this.Name = name;
		this.dob = dob;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public long getRegno() {
		return regno;
	}

	public void setRegno(long regno) {
		this.regno = regno;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	

}
