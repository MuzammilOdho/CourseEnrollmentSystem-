package org.example.ui;

import org.example.entity.Instructor;
import org.example.entity.Student;
import org.example.exception.EnrollmentException;
import org.example.service.EnrollmentService;
import org.example.service.InstructorService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StudentUI {
    private final StudentService studentService;
    private final InstructorService instructorService;
    private final InputHandler inputHandler;
    private final EnrollmentService enrollmentService;

@Autowired
    public StudentUI(StudentService studentService, InstructorService instructorService,
                     InputHandler inputHandler, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.inputHandler = inputHandler;
        this.enrollmentService = enrollmentService;
    }

    public void viewAvailableCourses() {
        try {

            Map<Integer, String> instructorNames = instructorService.getAllInstructors().stream()
                    .collect(Collectors.toMap(Instructor::getId, Instructor::getName));
            System.out.println("===== Available Courses =====");

            studentService.viewAllCourses().forEach(course -> {
                String instructorName = instructorNames.getOrDefault(course.getInstructorId(), "Unknown Instructor");
                System.out.printf("Course id: %d | Course Name: %s | Capacity: %d | Instructor: %s%n",
                        course.getId(), course.getName(), course.getCapacity(), instructorName);
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void enrollInCourse(int userId) {
        try {
            viewAvailableCourses();
            int courseId = inputHandler.readInt("Enter course id: ");

            enrollmentService.enrollStudent(userId, courseId);
            System.out.println("Enrollment successful!");

        } catch (EnrollmentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to enroll in course. Please try again later.");
        }
    }

    public void viewEnrolledCourses(int userId) {
        try {

            Map<Integer, String> instructorNames = instructorService.getAllInstructors().stream()
                    .collect(Collectors.toMap(Instructor::getId, Instructor::getName));

            System.out.println("===== Enrolled Courses =====");

            studentService.viewEnrolledCourses(userId).forEach(course -> {
                String instructorName = instructorNames.getOrDefault(course.getInstructorId(), "Unknown Instructor");
                System.out.printf("Course id: %d | Course Name: %s | Capacity: %d | Instructor: %s%n",
                        course.getId(), course.getName(), course.getCapacity(), instructorName);
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
