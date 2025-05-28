package org.example.service.impl;

import org.example.dao.CourseDao;
import org.example.dao.EnrollmentDao;
import org.example.dao.InstructorDao;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Student;
import org.example.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class instructorServiceImpl implements InstructorService {

    CourseDao courseDao;
    InstructorDao instructorDao;
    EnrollmentDao enrollmentDao;

    @Autowired
    public instructorServiceImpl(CourseDao courseDao, InstructorDao instructorDao, EnrollmentDao enrollmentDao) {
        this.courseDao = courseDao;
        this.instructorDao = instructorDao;
        this.enrollmentDao = enrollmentDao;
    }

    @Override
    public List<Course> viewAssignedCourses(int instructorId) {
       return courseDao.findByInstructorId(instructorId);
    }

    @Override
    public List<Student> viewEnrolledStudents(int instructorId) {
          return enrollmentDao.getEnrolledStudentsByInstructor(instructorId);
    }

    @Override
    public Instructor getInstructorById(int userId) {
      return   instructorDao.findByUserId(userId);
    }
}
