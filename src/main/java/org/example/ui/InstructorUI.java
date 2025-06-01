package org.example.ui;

import org.example.entity.Instructor;
import org.example.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructorUI {
    private final  InstructorService instructorService;

    @Autowired
    public InstructorUI(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    public void viewAssignedCourses(int instructorId) {
        try {
        System.out.println("======= Assigned Courses ========");
        instructorService.viewAssignedCourses(instructorId).forEach(System.out::println);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void viewEnrolledStudents(int instructorId) {
        try {
            System.out.println("======= Enrolled Students ========");
            instructorService.viewEnrolledStudents(instructorId).forEach(System.out::println);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
