package org.example.dao;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.exception.EnrollmentException;
import org.example.utills.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, studentId, courseId);
        } catch (DataAccessException e) {
            throw new EnrollmentException("Failed to enroll student ID " + studentId + " to course ID " + courseId, e);
        }
    }

    public List<Student> getStudentsForCourse(int courseId) {
        try {
            String sql = "SELECT s.id, s.user_id, s.full_name " +
                    "FROM students s " +
                    "JOIN enrollments e ON s.id = e.student_id " +
                    "WHERE e.course_id = ?";
            return jdbcTemplate.query(sql, RowMappers.STUDENT_ROW_MAPPER, courseId);
        } catch (DataAccessException e) {
            throw new EnrollmentException("Failed to retrieve students for course ID " + courseId, e);
        }
    }

    public List<Course> getCoursesForStudent(int studentId) {
        try {
            String sql = "SELECT c.id, c.title, c.instructor_id , c.capacity " +
                    "FROM courses c " +
                    "JOIN enrollments e ON c.id = e.course_id " +
                    "WHERE e.student_id = ?";
            return jdbcTemplate.query(sql, RowMappers.COURSE_ROW_MAPPER, studentId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new EnrollmentException("Failed to retrieve courses for student ID " + studentId, e);
        }
    }

    public boolean isAlreadyEnrolled(int studentId, int courseId) {
        try {
            String sql = "SELECT count(*) FROM enrollments WHERE student_id = ? AND course_id = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId, courseId);
            return count != null && count > 0;
        } catch (DataAccessException e) {
//            e.printStackTrace();
            throw new EnrollmentException("Failed to check enrollment for student ID " + studentId + " and course ID " + courseId, e);
        }
    }

    public List<Student> getEnrolledStudentsByInstructor(int instructorId) {
        try {
            String sql = """
                SELECT s.id, s.user_id, s.full_name
                FROM students s
                JOIN enrollments e ON s.id = e.student_id
                JOIN courses c ON e.course_id = c.id
                WHERE c.instructor_id = ?
            """;
            return jdbcTemplate.query(sql, RowMappers.STUDENT_ROW_MAPPER, instructorId);
        } catch (DataAccessException e) {
            throw new EnrollmentException("Failed to retrieve students enrolled under instructor ID " + instructorId, e);
        }
    }
}
