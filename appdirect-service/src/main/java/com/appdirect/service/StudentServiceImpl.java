package com.appdirect.service;

import com.appdirect.config.ViewMapper;
import com.appdirect.dto.StudentDTO;
import com.appdirect.entity.Student;
import com.appdirect.exception.ServiceException;
import com.appdirect.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ViewMapper<StudentDTO> studentDTOViewMapper;
    @Autowired
    private ViewMapper<Student> studentViewMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StudentDTO getStudentById(int id) throws ServiceException {
        Student student=studentRepository.findById(id);
        if(null==student){
            throw new ServiceException("Student Does Not Exist",new RuntimeException(), HttpServletResponse.SC_NOT_FOUND);
        }
        return studentDTOViewMapper.map(student,StudentDTO.class);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<StudentDTO> getAllStudents(){
        List<Student> students=(List<Student>) studentRepository.findAll();
        return  studentDTOViewMapper.maplist(students,StudentDTO.class);
    }
    @Override
    public StudentDTO createStudent(StudentDTO studentDto){
        Student student=studentViewMapper.map(studentDto,Student.class);
        return  studentDTOViewMapper.map(studentRepository.save(student),StudentDTO.class);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StudentDTO updateStudent(StudentDTO studentDto) throws ServiceException {
        Student student=studentRepository.findById(studentDto.getId());
        if(null== student){
            throw new ServiceException("Student Does Not Exist",new RuntimeException(),HttpServletResponse.SC_NOT_FOUND);
        }
        return  studentDTOViewMapper.map(studentRepository.save(studentViewMapper.map(studentDto,Student.class)),StudentDTO.class);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStudent(int id) throws ServiceException {
        Student student=studentRepository.findById(id);
        if(null== student){
            throw new ServiceException("Student Does Not Exist",new RuntimeException(),HttpServletResponse.SC_NOT_FOUND);
        }
        studentRepository.delete(id);
    }
}
