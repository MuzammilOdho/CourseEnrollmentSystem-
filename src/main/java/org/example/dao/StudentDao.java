package org.example.dao;

import org.example.entity.Student;
import org.example.exception.DatabaseException;
import org.example.exception.UserNotFoundException;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student student) {
        try {
            String sql = "INSERT INTO students (user_id, full_name) VALUES (?, ?)";
            jdbcTemplate.update(sql, student.getUserId(), student.getName());
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to save student with user ID: " + student.getUserId(), e);
        }
    }

    public Student findByUserId(int userId) {
        try {
            String sql = "SELECT id, user_id, full_name FROM students WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, RowMappers.STUDENT_ROW_MAPPER, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Student with user ID " + userId + " not found.");
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch student with user ID: " + userId, e);
        }
    }

    public List<Student> findAll() {
        try {
            String sql = "SELECT id, user_id, full_name FROM students";
            return jdbcTemplate.query(sql, RowMappers.STUDENT_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch list of students.", e);
        }
    }
}
