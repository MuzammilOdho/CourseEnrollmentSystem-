package org.example.dao;

import org.example.entity.Course;
import org.example.exception.CourseNotFoundException;
import org.example.exception.DatabaseException;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Course course) {
        try {
            String sql = "INSERT INTO courses (title, capacity, instructor_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, course.getName(), course.getCapacity(), course.getInstructorId());
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to save course: " + course.getName(), e);
        }
    }

    public List<Course> findAll() {
        try {
            String sql = "SELECT * FROM courses";
            return jdbcTemplate.query(sql, RowMappers.COURSE_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to retrieve courses.", e);
        }
    }

    public List<Course> findByInstructorId(int instructorId) {
        try {
            String sql = "SELECT * FROM courses WHERE instructor_id = ?";
            return jdbcTemplate.query(sql, RowMappers.COURSE_ROW_MAPPER, instructorId);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to retrieve courses for instructor ID: " + instructorId, e);
        }
    }

    public Course findById(int courseId) {
        try {
            String sql = "SELECT * FROM courses WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, RowMappers.COURSE_ROW_MAPPER, courseId);
        } catch (EmptyResultDataAccessException e) {
            throw new CourseNotFoundException(courseId);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to retrieve course with ID: " + courseId, e);
        }
    }


}
