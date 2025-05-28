package org.example.dao;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EnrollmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public void enrollStudent(int studentId, int courseId) {
        String sql = "Insert into enrollment (student_id, course_id) values (?, ?)";
        jdbcTemplate.update(sql, studentId, courseId);
    }

    public List<Student> getStudentsForCourse(int courseId) {
        String sql = "SELECT s.id, s.user_id, s.full_name " +
                "FROM student s " +
                "JOIN enrollment e ON s.id = e.student_id " +
                "WHERE e.course_id = ?";

        return jdbcTemplate.query(sql, RowMappers.STUDENT_ROW_MAPPER, courseId);
    }

    public List<Course> getCoursesForStudent(int studentId) {
        String sql = "SELECT c.id, c.name, c.instructor_id " +
                "FROM course c " +
                "JOIN enrollment e ON c.id = e.course_id " +
                "WHERE e.student_id = ?";
        return jdbcTemplate.query(sql, RowMappers.COURSE_ROW_MAPPER, studentId);
    }

    public boolean isAlreadyEnrolled(int studentId, int courseId) {
        String sql = "SELECT count(*) FROM enrollment WHERE student_id = ? AND course_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId, courseId);
        return count != null && count > 0;
    }

    public List<Student> getEnrolledStudentsByInstructor(int instructorId) {
        String sql = """
        SELECT s.id, s.user_id, s.full_name
        FROM students s
        JOIN enrollment e ON s.id = e.student_id
        JOIN courses c ON e.course_id = c.id
        WHERE c.instructor_id = ?
    """;
       return jdbcTemplate.query(sql, RowMappers.STUDENT_ROW_MAPPER, instructorId);
    }
}
