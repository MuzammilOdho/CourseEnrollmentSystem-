package org.example.service.impl;

import org.example.dao.CourseDao;
import org.example.dao.StudentDao;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.exception.UserNotFoundException;
import org.example.service.EnrollmentService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final EnrollmentService enrollmentService;
    private final CourseDao courseDao;
    @Autowired
    public StudentServiceImpl(StudentDao studentDao, EnrollmentService enrollmentService, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.enrollmentService = enrollmentService;
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> viewEnrolledCourses(int userId) {
        return enrollmentService.getCoursesForStudent(userId);
    }

    @Override
    public List<Course> viewAllCourses() {
       return courseDao.findAll();
    }

    @Override
    public Student getStudentById(int userId) {
        try {
        return studentDao.findByUserId(userId);

        }catch (UserNotFoundException e){
            throw new UserNotFoundException("Invalid student id");
        }

    }
}
