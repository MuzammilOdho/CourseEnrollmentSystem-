package org.example.service;

import org.example.entity.Course;
import org.example.entity.User;

public interface AdminService {

    void registerStudent(User user);
    void registerInstructor(User user);
    void createCourse(Course course);

}
