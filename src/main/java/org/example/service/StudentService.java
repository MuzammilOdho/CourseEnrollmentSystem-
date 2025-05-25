package org.example.service;

import org.example.entity.Course;
import org.example.entity.Student;

import java.util.List;

public interface StudentService {

    List<Course> viewEnrolledCourses(int studentId);
    List<Course> viewAllCourses();
    Student getStudentById(int studentId);

}
