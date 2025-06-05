package com.project.Exam_result.dto;

import java.time.LocalDate;

public class Request {
    private Long Regno;
    private LocalDate dob;

    // No-arg constructor
    public Request() {
    }

    // All-arg constructor
    public Request(Long Regno, LocalDate dob) {
        this.Regno = Regno;
        this.dob = dob;
    }

    // Getters and Setters
    public Long getRegno() {
        return Regno;
    }

    public void setRegno(Long Regno) {
        this.Regno = Regno;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
