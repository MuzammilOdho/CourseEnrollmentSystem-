package org.example.service.impl;

import org.example.dao.EnrollmentDao;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    EnrollmentDao enrollmentDao;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentDao enrollmentDao) {
        this.enrollmentDao = enrollmentDao;
    }



    @Override
    public void enrollStudent(int studentId, int courseId) {
        enrollmentDao.enrollStudent(studentId, courseId);
    }

    @Override
    public List<Course> getCoursesForStudent(int studentId) {
      return enrollmentDao.getCoursesForStudent(studentId);
    }

    @Override
    public List<Student> getStudentsForCourse(int courseId) {
        return enrollmentDao.getStudentsForCourse(courseId);
    }

    @Override
    public boolean isStudentAlreadyEnrolled(int studentId, int courseId) {
        return enrollmentDao.isAlreadyEnrolled(studentId, courseId);
    }
}
