package com.project.Exam_result.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }

    @GetMapping("teacher/hello")
    public String teacherHello() {
        return "Hello Teacher!";
    }

    @GetMapping("student/hello")
    public String studentHello() {
        return "Hello Student!";
    }
}
