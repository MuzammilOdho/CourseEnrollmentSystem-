package org.example.ui;

import org.example.entity.Student;
import org.example.service.EnrollmentService;
import org.example.service.InstructorService;
import org.example.service.StudentService;
import org.springframework.stereotype.Component;

@Component
public class StudentUI {
    private StudentService studentService;
    private InstructorService instructorService;
    private InputHandler inputHandler;
    private EnrollmentService enrollmentService;
    public void viewAvailableCourses() {
        System.out.println("===== Available Courses =====");
        studentService.viewAllCourses().forEach(course ->{
            System.out.printf("Course id: %d | Course Name: %s | Course Capacity: %d | Instructor Name: %s ",
                    course.getId(),
                    course.getName() ,
                    course.getCapacity(),
                    instructorService.getInstructorById(course.getInstructorId()).getName());
        });
    }

    public void enrollInCourse(int studentId) {
        viewAvailableCourses();
        int courseId = inputHandler.readInt("Enter course id: ");
        enrollmentService.enrollStudent(studentId,courseId);
    }

    public void viewEnrolledCourses(int studentId) {
        System.out.println("===== Enrolled Courses =====");
        studentService.viewEnrolledCourses(studentId).forEach(course ->{
            System.out.printf("Course id: %d | Course Name: %s | Course Capacity: %d | Instructor Name: %s ",
                    course.getId(),
                    course.getName() ,
                    course.getCapacity(),
                    instructorService.getInstructorById(course.getInstructorId()).getName());
        });
    }

}
