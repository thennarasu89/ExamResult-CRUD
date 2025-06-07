package com.project.Exam_result.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Input {
	
	
	private Long Regno;
	
	@NotNull(message = "/Mark1 is required")
	@Min(value = 0, message = "/must be postive value")
	@Max(value = 100, message = "/must be less than 100")
    private int mark1;
    
    @NotNull(message = "/Mark2 is required")
	@Min(value = 0, message = "/must be postive value")
	@Max(value = 100, message = "/must be less than 100")
    private int mark2;
    
    @NotNull(message = "/Mark3 is required")
	@Min(value = 0, message = "/must be postive value")
	@Max(value = 100, message = "/must be less than 100")
    private int mark3;
    
    @NotNull(message = "/Mark4 is required")
	@Min(value = 0, message = "/must be postive value")
	@Max(value = 100, message = "/must be less than 100")
    private int mark4;
    
    @NotNull(message = "/Mark5 is required")
	@Min(value = 0, message = "/must be postive value")
	@Max(value = 100, message = "/must be less than 100")
    private int mark5;
    
    @NotNull(message = "/Mark6 is required")
	@Min(value = 0, message = "/must be postive value")
	@Max(value = 100, message = "/must be less than 100")
    private int mark6;

    // No-arg constructor
    public Input() {
    }

    // All-arg constructor
    public Input(Long Regno, int mark1, int mark2, int mark3, int mark4, int mark5, int mark6) {
        this.Regno = Regno;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.mark4 = mark4;
        this.mark5 = mark5;
        this.mark6 = mark6;
    }

    // Getters and Setters
    public Long getRegno() {
        return Regno;
    }

    public void setRegno(Long Regno) {
        this.Regno = Regno;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public int getMark4() {
        return mark4;
    }

    public void setMark4(int mark4) {
        this.mark4 = mark4;
    }

    public int getMark5() {
        return mark5;
    }

    public void setMark5(int mark5) {
        this.mark5 = mark5;
    }

    public int getMark6() {
        return mark6;
    }

    public void setMark6(int mark6) {
        this.mark6 = mark6;
    }
}
