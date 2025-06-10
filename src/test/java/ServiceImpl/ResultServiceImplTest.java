package ServiceImpl;


import com.project.Exam_result.Entity.Result;
import com.project.Exam_result.Entity.Student;
import com.project.Exam_result.Exception.ResourceNotFound;
import com.project.Exam_result.Repo.ResultRepo;
import com.project.Exam_result.Repo.StudentRepo;
import com.project.Exam_result.Service.impl.ResultServiceImpl;
import com.project.Exam_result.dto.Input;
import com.project.Exam_result.dto.Request;
import com.project.Exam_result.dto.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private ResultRepo resultRepo;

    @InjectMocks
    private ResultServiceImpl resultService;

    private Student student;
    private Result result;
    private Request request;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setRegno(101L);
        student.setName("John");
        student.setDob(LocalDate.of(2005, 1, 1));

        result = new Result();
        result.setRegno(101L);
        result.setMark1(80);
        result.setMark2(75);
        result.setMark3(85);
        result.setMark4(90);
        result.setMark5(95);
        result.setMark6(88);
        result.setTotal(513);
        result.setResult("Pass");

        request = new Request();
        request.setRegno(101L);
        request.setDob(LocalDate.of(2005, 1, 1));
    }

   
    @Test
    void testSaveResult_Success() {
        Input input = new Input();
        input.setRegno(101L);
        input.setMark1(80);
        input.setMark2(75);
        input.setMark3(85);
        input.setMark4(90);
        input.setMark5(95);
        input.setMark6(88);

        when(studentRepo.findById(101L)).thenReturn(Optional.of(student));
        when(resultRepo.save(any(Result.class))).thenReturn(result);

        assertDoesNotThrow(() -> resultService.saveResult(input));
        verify(resultRepo, times(1)).save(any(Result.class));
    }

   
    @Test
    void testFetchResult_Success() {
        when(studentRepo.findById(101L)).thenReturn(Optional.of(student));
        when(resultRepo.findById(101L)).thenReturn(Optional.of(result));

        Response response = resultService.fetchResult(request);

        assertEquals("John", response.getName());
        assertEquals(513, response.getTotal());
        assertEquals("Pass", response.getResult());
    }

    
    @Test
    void testFetchResult_StudentNotFound_ShouldThrowException() {
        Long regNo = 123L;
        Request request = new Request();
        request.setRegno(regNo);

        when(studentRepo.findById(regNo)).thenReturn(Optional.empty());

        ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
            resultService.fetchResult(request);
        });

        assertEquals("Student not found with Register number:" + regNo, exception.getMessage());
    }

    @Test
    void testFetchResult_WrongDOB_ShouldThrowException() {
        request.setDob(LocalDate.of(2004, 1, 1)); 

        when(studentRepo.findById(101L)).thenReturn(Optional.of(student));

        ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
            resultService.fetchResult(request);
        });

        assertEquals("Enter Correct DOB for Register number:" + 101L, exception.getMessage());
    }
}
