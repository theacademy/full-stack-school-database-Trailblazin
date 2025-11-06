package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.StudentMapper;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        //YOUR CODE STARTS HERE
        final String INSERT_INTO_STUDENT = "INSERT INTO student(fName, lName) VALUES(?,?)";
        jdbcTemplate.update(INSERT_INTO_STUDENT,
                student.getStudentFirstName(),
                student.getStudentLastName());

        //int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        //student.setStudentId(newId);
        return student;
        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
        return jdbcTemplate.query(SELECT_ALL_STUDENTS, new StudentMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE sid = ?";
            return jdbcTemplate.queryForObject(SELECT_STUDENT_BY_ID, new StudentMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateStudent(Student student) {
        //YOUR CODE STARTS HERE

        final String UPDATE_STUDENT = "UPDATE student SET fName = ?, lName = ? WHERE sid = ?";
        jdbcTemplate.update(UPDATE_STUDENT,
                student.getStudentFirstName(),
                student.getStudentLastName(),
                student.getStudentId());
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student "
                + "WHERE course_id = ?";
        jdbcTemplate.update(DELETE_COURSE_STUDENT, id);

        final String DELETE_STUDENT = "DELETE FROM student WHERE sid = ?";
        jdbcTemplate.update(DELETE_STUDENT, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        final String INSERT_STUDENT_COURSE = "INSERT INTO course_student"
                + "(student_id, course_id) VALUES(?,?)";
        jdbcTemplate.update(INSERT_STUDENT_COURSE, studentId, courseId);


        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        // Delete the link between the student and course in the bridge table
        final String DELETE_COURSE_STUDENT =
                "DELETE FROM course_student WHERE courseId = ? AND studentId = ?";

        jdbcTemplate.update(DELETE_COURSE_STUDENT, courseId, studentId);

        //YOUR CODE ENDS HERE
    }
}
