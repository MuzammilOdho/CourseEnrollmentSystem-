package org.example.service;

import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Student;
import org.example.entity.User;

import java.util.List;

public interface AdminService {

    void registerStudent(User user);
    void registerInstructor(User user);
    void createCourse(Course course);
    List<Instructor> getAllInstructors();
    List<Course> getAllCourses();
    List<Student> getAllStudents();
}
