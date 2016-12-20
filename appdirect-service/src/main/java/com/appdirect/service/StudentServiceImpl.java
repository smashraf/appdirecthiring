package com.appdirect.service;

import com.appdirect.config.ViewMapper;
import com.appdirect.dto.StudentDTO;
import com.appdirect.entity.Student;
import com.appdirect.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ViewMapper<StudentDTO> studentDTOViewMapper;
    @Override
    public StudentDTO getStudentById(String id) {
        Student student=studentRepository.findById(id);
        return studentDTOViewMapper.map(student,StudentDTO.class);
    }
    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> students=(List<Student>) studentRepository.findAll();
        return  studentDTOViewMapper.maplist(students,StudentDTO.class);
    }
}
