package org.example.service.impl;

import org.example.dao.*;
import org.example.entity.*;
import org.example.exception.DatabaseException;
import org.example.exception.UserNotFoundException;
import org.example.service.AdminService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserDao userDao;
    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final InstructorDao instructorDao;
    private final UserService userService;

    @Autowired
    public AdminServiceImpl(UserDao userDao, CourseDao courseDao,
                            StudentDao studentDao, InstructorDao instructorDao,
                            UserService userService) {
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.instructorDao = instructorDao;
        this.userService = userService;
    }




    @Override
    public void registerStudent(User user) {
        userService.register(user);
        try {
            User savedUser = userDao.findByUsername(user.getUsername());
            studentDao.save(new Student(user.getUsername(), savedUser.getId()));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Failed to register student " + user.getUsername());
        } catch (Exception e) {
            throw new DatabaseException("Failed to register student: " + user.getUsername(), e);
        }
    }

    @Override
    public void registerInstructor(User user) {
        userService.register(user);
        User savedUser = userDao.findByUsername(user.getUsername());
        instructorDao.save(new Instructor(user.getUsername(), savedUser.getId()));

    }

    @Override
    public void createCourse(Course course) {
        try {
            instructorDao.findByUserId(course.getInstructorId());
            courseDao.save(course);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Invalid Instructor Id");
        } catch (Exception e) {
            throw new DatabaseException("Failed to create course: " + course.getName(), e);
        }
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorDao.findAll();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll(); // handled inside CourseDao
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll(); // handled inside StudentDao
    }
}
