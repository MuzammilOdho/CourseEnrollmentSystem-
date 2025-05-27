package org.example.dao;

import org.example.entity.Course;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "INSERT INTO courses (title, capacity, instructor_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, course.getName(), course.getCapacity(), course.getInstructorId());
    }

    public List<Course> findByInstructorId(int instructorId) {
        String sql = "SELECT * FROM courses WHERE instructor_id = ?";
        return jdbcTemplate.query(sql, RowMappers.COURSE_ROW_MAPPER, instructorId);
    }
}
