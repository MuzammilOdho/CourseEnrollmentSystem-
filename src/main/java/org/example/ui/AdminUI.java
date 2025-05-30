package org.example.ui;

import org.example.entity.*;
import org.example.service.AdminService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminUI {


    AdminService adminService;
    InputHandler inputHandler;

    public void registerStudent() {
      String username =  inputHandler.readString("Enter student ID: ");
      String password = inputHandler.readString("Enter student password: ");
      adminService.registerStudent(new User(username,password, Role.STUDENT));
    }

    public void registerInstructor() {
        String username =  inputHandler.readString("Enter Instructor name: ");
        String password = inputHandler.readString("Enter instructor password: ");
        adminService.registerInstructor(new User(username,password, Role.INSTRUCTOR));
    }

    public void addCourse() {
       getAllInstructors();
        String courseName = inputHandler.readString("Enter Course name: ");
        int capacity = inputHandler.readInt("Enter capacity: ");
        int instructorId = inputHandler.readInt("Enter instructor ID: ");
        adminService.createCourse(new Course(courseName,capacity,instructorId));
    }

    public void getAllCourses() {
        System.out.println("===== Registered Courses =====");
        adminService.getAllCourses().forEach(System.out::println);
    }

    public void getAllStudents(){
        System.out.println("===== Registered Students =====");
        adminService.getAllStudents().forEach(System.out::println);
    }

    public void getAllInstructors(){
        System.out.println("===== Registered Instructors =====");
      adminService.getAllInstructors().forEach(System.out::println);
    }

}