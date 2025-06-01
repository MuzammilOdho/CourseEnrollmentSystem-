package org.example.dao;

import org.example.entity.Instructor;
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
public class InstructorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InstructorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Instructor instructor) {
        try {
            String sql = "INSERT INTO instructors (user_id, full_name) VALUES (?, ?)";
            jdbcTemplate.update(sql, instructor.getUserId(), instructor.getName());
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to save instructor with user ID: " + instructor.getUserId(), e);
        }
    }

    public Instructor findByUserId(int userId) {
        try {
            String sql = "SELECT * FROM instructors WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, RowMappers.Instructor_ROW_MAPPER, userId);
        } catch (EmptyResultDataAccessException e) {

            throw new UserNotFoundException("Instructor with user ID " + userId + " not found.");
        }
        catch (DataAccessException e) {
            throw new DatabaseException("Failed to find instructor with user ID: " + userId, e);
        }
    }

    public List<Instructor> findAll() {
        try {
            String sql = "SELECT * FROM instructors";
            return jdbcTemplate.query(sql, RowMappers.Instructor_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch instructors from the database.", e);
        }
    }
}
