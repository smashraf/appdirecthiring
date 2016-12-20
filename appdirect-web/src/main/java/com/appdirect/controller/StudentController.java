package com.appdirect.controller;

import com.appdirect.config.ViewMapper;
import com.appdirect.dto.StudentDTO;
import com.appdirect.representation.StudentRepresentation;
import com.appdirect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Sample Student Controller
 */
@Controller
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private ViewMapper<StudentRepresentation> studentRepresentationViewMapper;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/students",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentRepresentation> getStudents() {
        List<StudentDTO> studentDTOs=studentService.getAllStudents();
        return studentRepresentationViewMapper.maplist(studentDTOs,StudentRepresentation.class);
    }

    @RequestMapping(value="/students/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StudentRepresentation getStudent(@RequestParam String id) {
        return studentRepresentationViewMapper.map(studentService.getStudentById(id),StudentRepresentation.class);
    }
}
