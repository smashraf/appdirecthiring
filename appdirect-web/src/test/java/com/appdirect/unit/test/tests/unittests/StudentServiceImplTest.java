package com.appdirect.unit.test.tests.unittests;

import com.appdirect.dto.StudentDTO;
import com.appdirect.exception.ServiceException;
import com.appdirect.service.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceImplTest {
    @Autowired
    StudentServiceImpl studentService;

    @Test
    public void testcreateStudent(){
        StudentDTO studentDto=new StudentDTO();
        studentDto.setLastName("testlastname");
        studentDto.setFirstName("testfirstname");
        StudentDTO createdStudent=studentService.createStudent(studentDto);
        assertThat(createdStudent.getId(),notNullValue());
    }

    @Test
    public void testupdateStudent() throws ServiceException {
        StudentDTO student = studentService.getStudentById(1);
        student.setFirstName("UpdatedFirstName");
        student.setLastName("UpdatedLastName");
        StudentDTO updatedStudent=studentService.updateStudent(student);
        assertThat(student.getFirstName(),equalTo(updatedStudent.getFirstName()));
        assertThat(student.getLastName(),equalTo(updatedStudent.getLastName()));

    }

    @Test(expected=ServiceException.class)
    public void testdeleteStudent() throws ServiceException {
        StudentDTO student = studentService.getStudentById(1);
        studentService.deleteStudent(student.getId());
        studentService.getStudentById(1);

    }
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
