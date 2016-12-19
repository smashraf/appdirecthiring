package com.appdirect.repository;

import com.appdirect.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by covacsis on 18/12/16.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Student findById(String id);
    public Student findByFirstNameAndLastName(String firstName,String LastName);
}
