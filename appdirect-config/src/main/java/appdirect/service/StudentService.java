package appdirect.service;

import com.appdirect.dto.StudentDTO;
import com.appdirect.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public StudentDTO getStudentById(int id) throws ServiceException;
    public List<StudentDTO> getAllStudents();
    public StudentDTO createStudent(StudentDTO studentDto);

    public StudentDTO updateStudent(StudentDTO studentDto) throws ServiceException;

     public void deleteStudent(int id) throws ServiceException;
}
