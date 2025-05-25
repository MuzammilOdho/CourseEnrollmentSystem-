package org.example.service;

import org.example.entity.Course;
import org.example.entity.Student;

import java.util.List;

public interface EnrollmentService {
    void enrollStudent(int studentId, int courseId);
    List<Course> getCoursesForStudent(int studentId);
    List<Student> getStudentsForCourse(int courseId);
    boolean isStudentAlreadyEnrolled(int studentId, int courseId);

}
