package org.example.exception;

public class CourseNotFoundException extends RuntimeException {
  public CourseNotFoundException(int courseId) {
    super("Course with ID " + courseId + " not found.");
  }
}
