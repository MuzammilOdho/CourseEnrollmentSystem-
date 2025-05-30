package org.example.ui;

import org.example.entity.Instructor;
import org.example.service.InstructorService;
import org.springframework.stereotype.Component;

@Component
public class InstructorUI {
    private InstructorService instructorService;
    public void viewAssignedCourses(int instructorId) {
        System.out.println("======= Assigned Courses ========");
        instructorService.viewAssignedCourses(instructorId).forEach(System.out::println);
    }

    public void viewEnrolledStudents(int instructorId) {
        System.out.println("======= Enrolled Students ========");
        instructorService.viewEnrolledStudents(instructorId).forEach(System.out::println);
    }
}
