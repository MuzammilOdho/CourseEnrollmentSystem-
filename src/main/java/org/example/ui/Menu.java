package org.example.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final InputHandler inputHandler;

    @Autowired
    public Menu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public int displayLoginMenu() {
        System.out.println("===== Course Enrollment System =====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        return inputHandler.readInt("Choose an option: ");
    }

    public int displayAdminMenu() {
        System.out.println("===== Admin Menu =====");
        System.out.println("1. Register Student");
        System.out.println("2. Register Instructor");
        System.out.println("3. Add Course");
        System.out.println("4. List Courses");
        System.out.println("5. List Students");
        System.out.println("6. List Instructors");
        System.out.println("0. Logout");
        return inputHandler.readInt("Choose an option: ");
    }

    public int displayStudentMenu() {
        System.out.println("===== Student Menu =====");
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. View Enrolled Courses");
        System.out.println("0. Logout");
        return inputHandler.readInt("Choose an option: ");
    }

    public int displayInstructorMenu() {
        System.out.println("===== Instructor Menu =====");
        System.out.println("1. View Assigned Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("0. Logout");
        return inputHandler.readInt("Choose an option: ");
    }
}
