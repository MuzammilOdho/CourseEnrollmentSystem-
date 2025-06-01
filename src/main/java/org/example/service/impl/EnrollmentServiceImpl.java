package org.example.service.impl;

import org.example.dao.CourseDao;
import org.example.dao.EnrollmentDao;
import org.example.dao.StudentDao;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.exception.CourseNotFoundException;
import org.example.exception.EnrollmentException;
import org.example.exception.UserNotFoundException;
import org.example.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDao enrollmentDao;
    private final StudentDao studentDao;
    private final CourseDao courseDao;


    public EnrollmentServiceImpl(EnrollmentDao enrollmentDao, StudentDao studentDao, CourseDao courseDao) {
        this.enrollmentDao = enrollmentDao;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }


    @Override
    public void enrollStudent(int userId, int courseId) {
        try {
            courseDao.findById(courseId);
          int studentId  =  studentDao.findByUserId(userId).getId();
            if (enrollmentDao.isAlreadyEnrolled(studentId, courseId)) {
                throw new EnrollmentException("Student with ID " + studentId +
                        " is already enrolled in course with ID " + courseId, null);
            }
            enrollmentDao.enrollStudent(studentId, courseId);
        }catch (CourseNotFoundException c){
            throw new EnrollmentException("Invalid course ID",c);
        }
    }

    @Override
    public List<Course> getCoursesForStudent(int userId) {
        int studentId = studentDao.findByUserId(userId).getId();
        return enrollmentDao.getCoursesForStudent(studentId);
    }

    @Override
    public List<Student> getStudentsForCourse(int courseId) {
        return enrollmentDao.getStudentsForCourse(courseId);
    }

}
