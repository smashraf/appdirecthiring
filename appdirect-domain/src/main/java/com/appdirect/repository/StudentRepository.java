package com.appdirect.repository;

import com.appdirect.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Student findById(int id);
    public Student findByFirstNameAndLastName(String firstName,String LastName);
}
