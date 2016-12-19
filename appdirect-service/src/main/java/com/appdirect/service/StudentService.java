package com.appdirect.service;

import com.appdirect.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by covacsis on 18/12/16.
 */
@Service
public interface StudentService {
    public StudentDTO getStudentById(String id);
    public List<StudentDTO> getAllStudents();
}
