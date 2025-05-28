package org.example.service.impl;

import org.example.dao.CourseDao;
import org.example.dao.InstructorDao;
import org.example.dao.StudentDao;
import org.example.dao.UserDao;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Student;
import org.example.entity.User;
import org.example.service.AdminService;


public class AdminServiceImpl implements AdminService {

    private final UserDao userDao;
    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final InstructorDao instructorDao;

    public AdminServiceImpl(UserDao userDao, CourseDao courseDao, StudentDao studentDao,InstructorDao instructorDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.instructorDao = instructorDao;
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
    @Override
    public void registerStudent(User user) {
        saveUser(user);
        studentDao.save(new Student(
                user.getUsername(),
                userDao.findByUsername(user.getUsername()).getId())
        );
    }

    @Override
    public void registerInstructor(User user) {
        saveUser(user);
        instructorDao.save(new Instructor(
                user.getUsername(),
                userDao.findByUsername(user.getUsername()).getId()
                )
        );
    }

    @Override
    public void createCourse(Course course) {
        courseDao.save(course);
    }
}
