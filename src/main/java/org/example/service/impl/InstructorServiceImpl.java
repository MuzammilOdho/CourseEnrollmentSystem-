package org.example.service.impl;

import org.example.dao.CourseDao;
import org.example.dao.EnrollmentDao;
import org.example.dao.InstructorDao;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Student;
import org.example.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final CourseDao courseDao;
    private final InstructorDao instructorDao;
    private final EnrollmentDao enrollmentDao;

    @Autowired
    public InstructorServiceImpl(CourseDao courseDao, InstructorDao instructorDao, EnrollmentDao enrollmentDao) {
        this.courseDao = courseDao;
        this.instructorDao = instructorDao;
        this.enrollmentDao = enrollmentDao;
    }

    @Override
    public List<Course> viewAssignedCourses(int userId) {
        return courseDao.findByInstructorId(getInstructorById(userId).getId());

    }

    @Override
    public List<Student> viewEnrolledStudents(int userId) {
        return enrollmentDao.getEnrolledStudentsByInstructor(getInstructorById(userId).getId());
    }

    @Override
    public Instructor getInstructorById(int userId) {
        return instructorDao.findByUserId(userId);
    }

    public List<Instructor> getAllInstructors() {
        return instructorDao.findAll();
    }
}
