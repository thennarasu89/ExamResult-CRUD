package com.project.Exam_result.Service;
import com.project.Exam_result.dto.*;

public interface ResultService {
    void saveResult(Input input);
    Response fetchResult(Request dto);
    void updateResult(Input dto);
    void deleteResult(Long Regno);
}
