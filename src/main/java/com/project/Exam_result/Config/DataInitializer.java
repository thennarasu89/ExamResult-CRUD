package com.project.Exam_result.Config;

import com.project.Exam_result.Entity.Role;
import com.project.Exam_result.Entity.User;
import com.project.Exam_result.Repo.RoleRepo;
import com.project.Exam_result.Repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepo.save(adminRole);
            }

            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Collections.singleton(roleRepo.findByName("ROLE_ADMIN").get()));
                userRepo.save(admin);
            }
        };
    }
}
