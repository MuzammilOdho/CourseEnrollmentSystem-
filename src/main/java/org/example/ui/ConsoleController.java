package org.example.ui;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleController {

        private final UserUI userUI;
        private final AdminUI adminUI;
        private final StudentUI studentUI;
        private final InstructorUI instructorUI;
        private final Menu menu;

        @Autowired
    public ConsoleController(UserUI userUI, AdminUI adminUI,
                             StudentUI studentUI, InstructorUI instructorUI,
                             Menu menu) {
        this.userUI = userUI;
        this.adminUI = adminUI;
        this.studentUI = studentUI;
        this.instructorUI = instructorUI;
        this.menu = menu;
    }

    public void start() {
            boolean running = true;

            while (running) {
                int choice = menu.displayLoginMenu();

                switch (choice) {
                    case 1 -> {
                        User user = userUI.login();
                        if (user != null) {
                            switch (user.getRole()) {
                                case ADMIN -> handleAdminMenu();
                                case STUDENT -> handleStudentMenu(user.getId());
                                case INSTRUCTOR -> handleInstructorMenu(user.getId());
                                default -> System.out.println("Unknown role: " + user.getRole());
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("Exiting... Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }

            }
        }

        private void handleAdminMenu() {
            boolean back = false;
            while (!back) {
                int choice = menu.displayAdminMenu();
                switch (choice) {
                    case 1 -> adminUI.registerStudent();
                    case 2 -> adminUI.registerInstructor();
                    case 3 -> adminUI.addCourse();
                    case 4 -> adminUI.getAllCourses();
                    case 5 -> adminUI.getAllStudents();
                    case 6 -> adminUI.getAllInstructors();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice!");
                }
            }
        }

        private void handleStudentMenu(int studentId) {
            boolean back = false;
            while (!back) {
                int choice = menu.displayStudentMenu();
                switch (choice) {
                    case 1 -> studentUI.viewAvailableCourses();
                    case 2 -> studentUI.enrollInCourse(studentId);
                    case 3 -> studentUI.viewEnrolledCourses(studentId);
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice!");
                }
            }
        }

        private void handleInstructorMenu(int instructorId) {
            boolean back = false;
            while (!back) {
                int choice = menu.displayInstructorMenu();
                switch (choice) {
                    case 1 -> instructorUI.viewAssignedCourses(instructorId);
                    case 2 -> instructorUI.viewEnrolledStudents(instructorId);
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }