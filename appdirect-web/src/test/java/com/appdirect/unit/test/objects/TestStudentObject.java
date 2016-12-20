package com.appdirect.unit.test.objects;


import com.appdirect.entity.Student;

public class TestStudentObject {
    public static Student buildTestStud() {
        Student student =new Student();
        student.setLastName("TestLastName");
        student.setFirstName("TestFirstName");
        student.setId(1);
        return student;
    }
}
