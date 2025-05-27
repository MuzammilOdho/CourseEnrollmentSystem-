package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public List<Course> viewEnrolledCourses(int studentId) {
        return List.of();
    }

    @Override
    public List<Course> viewAllCourses() {
        return List.of();
    }

    @Override
    public Student getStudentById(int studentId) {
        return null;
    }
}
