package org.example.ui;

import org.example.entity.*;
import org.example.exception.DatabaseException;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserNotFoundException;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminUI {


    AdminService adminService;
    InputHandler inputHandler;

    @Autowired
    public AdminUI(AdminService adminService, InputHandler inputHandler) {
        this.adminService = adminService;
        this.inputHandler = inputHandler;
    }

    public void registerStudent() {
        try {
        String username =  inputHandler.readString("Enter student ID: ");
        String password = inputHandler.readString("Enter student password: ");
        adminService.registerStudent(new User(username,password, Role.STUDENT));
            System.out.println("Student registered successfully");
        }catch (UserNotFoundException | DatabaseException | UserAlreadyExistException e){
            System.out.println(e.getMessage());
        }
    }

    public void registerInstructor() {
        try {
            String username = inputHandler.readString("Enter Instructor name: ");
            String password = inputHandler.readString("Enter instructor password: ");
            adminService.registerInstructor(new User(username, password, Role.INSTRUCTOR));
            System.out.println("Instructor registered successfully");
        }catch (UserNotFoundException | DatabaseException | UserAlreadyExistException  e ){
            System.out.println(e.getMessage());
        }
    }

    public void addCourse() {
        try {
            getAllInstructors();
            String courseName = inputHandler.readString("Enter Course name: ");
            int capacity = inputHandler.readInt("Enter capacity: ");
            int instructorId = inputHandler.readInt("Enter instructor ID: ");
            adminService.createCourse(new Course(courseName, capacity, instructorId));
            System.out.println("Course added successfully");
        }catch (UserNotFoundException | DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllCourses() {
        try {

            System.out.println("===== Registered Courses =====");
            adminService.getAllCourses().forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllStudents(){
        try {
        System.out.println("===== Registered Students =====");
        adminService.getAllStudents().forEach(System.out::println);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllInstructors(){
        try {
            System.out.println("===== Registered Instructors =====");
            adminService.getAllInstructors().forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}