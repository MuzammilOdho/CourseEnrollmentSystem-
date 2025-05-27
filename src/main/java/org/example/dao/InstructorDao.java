package org.example.dao;

import org.example.entity.Instructor;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InstructorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Instructor instructor) {
        String sql = "INSERT INTO instructors (user_id, full_name) VALUES (?,?)";
        jdbcTemplate.update(sql,instructor.getUserId(),instructor.getName());
    }
    public Instructor findByUserId(int userId) {
        String sql = "SELECT * FROM instructors WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, RowMappers.Instructor_ROW_MAPPER, userId);
    }

}
