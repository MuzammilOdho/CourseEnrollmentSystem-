package org.example.dao;

import org.example.entity.User;
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
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String username, String password, String role) {
        try {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, username, password, role);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to save user: " + username, e);
        }
    }

    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(sql, RowMappers.USER_ROW_MAPPER, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(username);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch user: " + username, e);
        }
    }

    public List<User> findAll() {
        try {
            String sql = "SELECT * FROM users";
            return jdbcTemplate.query(sql, RowMappers.USER_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to retrieve users.", e);
        }
    }
}
