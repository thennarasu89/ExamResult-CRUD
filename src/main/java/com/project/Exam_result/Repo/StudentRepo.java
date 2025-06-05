package com.project.Exam_result.Repo;
import com.project.Exam_result.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import java.time.LocalDate;


public interface StudentRepo extends JpaRepository<Student, Long>  {
	Optional<Student> findByRegnoAndDob(Long regno, LocalDate dob);

}
