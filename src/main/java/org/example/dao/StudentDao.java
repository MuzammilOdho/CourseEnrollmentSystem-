package org.example.dao;

import org.example.entity.Student;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

   private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Student student) {
        String sql = "INSERT INTO students (user_id,full_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getUserId(), student.getName());
    }


    public Student findByUserId(int userId) {
        String sql = "SELECT id, user_id , full_name FROM students WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql,RowMappers.STUDENT_ROW_MAPPER,userId );
    }


}
