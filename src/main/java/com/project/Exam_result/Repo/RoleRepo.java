package com.project.Exam_result.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Exam_result.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
