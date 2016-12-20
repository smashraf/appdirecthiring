package com.appdirect.controller;

import com.appdirect.config.ViewMapper;
import com.appdirect.dto.StudentDTO;
import com.appdirect.exception.ServiceException;
import com.appdirect.representation.ApiResponse;
import com.appdirect.representation.StudentRepresentation;
import com.appdirect.service.StudentService;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample Student Controller
 */
@Controller
@RequestMapping("/api/v1")
public class StudentController {
    private static final Logger logger =  LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private ViewMapper<StudentRepresentation> studentRepresentationViewMapper;

    @Autowired
    private ViewMapper<StudentDTO> studentDTOViewMapper;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/students",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse getStudents() {
        List<StudentDTO> studentDTOs=studentService.getAllStudents();
        ApiResponse<StudentRepresentation> apiResponse=new ApiResponse<StudentRepresentation>();
        apiResponse.setMessage("Operation Successfull");
        apiResponse.setRepresentation(studentRepresentationViewMapper.maplist(studentDTOs,StudentRepresentation.class));
        apiResponse.setResponseCode(HttpServletResponse.SC_OK);
        return apiResponse;
    }

    @RequestMapping(value="/students/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse getStudent(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
        ApiResponse<StudentRepresentation> apiResponse=new ApiResponse<StudentRepresentation>();
        StudentRepresentation studentRepresentation;
        List<StudentRepresentation> studentRepresentations=new ArrayList<StudentRepresentation>();
        try {
            studentRepresentations.add(studentRepresentationViewMapper.map(studentService.getStudentById(id),StudentRepresentation.class));
            apiResponse.setMessage("Operation Successfull");
            apiResponse.setRepresentation(studentRepresentations);
            apiResponse.setResponseCode(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResponseCode(e.getCode());
            logger.error(e.getMessage());
        }
        return apiResponse;
    }
    @RequestMapping(value="/students",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StudentRepresentation getStudent(@RequestBody StudentRepresentation studentRepresentation) {
        ApiResponse<StudentRepresentation> apiResponse=new ApiResponse<StudentRepresentation>();
        List<StudentRepresentation> studentRepresentations=new ArrayList<StudentRepresentation>();
        studentRepresentations.add(studentRepresentationViewMapper.map(studentService.createStudent(studentDTOViewMapper.map(studentRepresentation,StudentDTO.class)),StudentRepresentation.class));
        apiResponse.setMessage("Operation Successfull");
        apiResponse.setRepresentation(studentRepresentations);
        apiResponse.setResponseCode(HttpServletResponse.SC_OK);
        return studentRepresentationViewMapper.map(studentService.createStudent(studentDTOViewMapper.map(studentRepresentation,StudentDTO.class)),StudentRepresentation.class);
    }
    @RequestMapping(value="/students/{id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse updateStudent(@PathVariable int id,@RequestBody StudentRepresentation studentRepresentation) {
        ApiResponse<StudentRepresentation> apiResponse=new ApiResponse<StudentRepresentation>();
        List<StudentRepresentation> studentRepresentations=new ArrayList<StudentRepresentation>();
        try {
            studentRepresentations.add(studentRepresentationViewMapper.map(studentService.updateStudent(studentDTOViewMapper.map(studentRepresentation,StudentDTO.class)),StudentRepresentation.class));
            apiResponse.setMessage("Operation Successfull");
            apiResponse.setRepresentation(studentRepresentations);
            apiResponse.setResponseCode(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResponseCode(e.getCode());
            logger.error(e.getMessage());
        }
        return apiResponse;
    }
    @RequestMapping(value="/students/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse deleteStudent(@PathVariable int id) {
        ApiResponse<StudentRepresentation> apiResponse=new ApiResponse<StudentRepresentation>();
        try {
            studentService.deleteStudent(id);
            apiResponse.setMessage("Operation Successfull");
            apiResponse.setResponseCode(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResponseCode(e.getCode());
            logger.error(e.getMessage());
        }
        return apiResponse;
    }
}
