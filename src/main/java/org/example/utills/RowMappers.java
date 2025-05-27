package org.example.utills;

import org.example.entity.*;
import org.springframework.jdbc.core.RowMapper;


public class RowMappers {
    public static final RowMapper<Student> STUDENT_ROW_MAPPER = (rs, rowNum) -> new Student(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("full_name")
    );

    public static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            Role.valueOf(rs.getString("role"))
    );

    public static final RowMapper<Instructor> Instructor_ROW_MAPPER = (rs, rowNum) -> new Instructor(
            rs.getInt("id"),
            rs.getString("full_name"),
            rs.getInt("user_id")
    );

    public static final RowMapper<Course> COURSE_ROW_MAPPER = (rs, rowNum) -> new Course(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getInt("capacity"),
            rs.getInt("instructor_id")
    );

}

