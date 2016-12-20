package com.appdirect.unit.test.tests;

import com.appdirect.dto.StudentDTO;
import com.appdirect.exception.ServiceException;
import com.appdirect.service.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.Lifecycle;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceImplTest{
    @Autowired
    StudentServiceImpl studentService;


    @Test
    public void testgetStudentById() throws ServiceException {
        StudentDTO student = studentService.getStudentById(1);
        assertThat(student.getId(),equalTo(1));
    }

    @Test
    public void testgetAllStudents(){
        List<StudentDTO> studentDTOs=studentService.getAllStudents();
        assertThat(studentDTOs,notNullValue());
    }

}
