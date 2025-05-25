package org.example.service;

import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Student;

public interface AdminService {

    void registerStudent(Student student);
    void registerInstructor(Instructor instructor);
    void createCourse(Course course);

}
