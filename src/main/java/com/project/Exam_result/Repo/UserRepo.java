package com.project.Exam_result.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Exam_result.Entity.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
