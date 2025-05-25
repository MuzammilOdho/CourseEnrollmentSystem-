package org.example.service;

import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Student;

import java.util.List;

public interface InstructorService {

    List<Course> viewAssignedCourses(int instructorId);
    List<Student> viewEnrolledStudents(int instructorId);
    Instructor getInstructorById(int instructorId);

}
