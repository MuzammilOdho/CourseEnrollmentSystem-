package org.example.dao;

import org.example.entity.User;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, username, password, role);
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,RowMappers.USER_ROW_MAPPER , username);
    }


    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, RowMappers.USER_ROW_MAPPER);
    }
}


