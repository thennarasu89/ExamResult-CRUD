package com.project.Exam_result.Controller;

import com.project.Exam_result.Entity.Role;
import com.project.Exam_result.Entity.User;
import com.project.Exam_result.Repo.RoleRepo;
import com.project.Exam_result.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepo.findByName("STUDENT").orElseThrow();
        user.setRoles(Collections.singleton(userRole));
        userRepo.save(user);
        return "User registered successfully!";
    }
}
