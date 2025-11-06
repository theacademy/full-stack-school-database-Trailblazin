package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.CourseMapper;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;
    private CourseDao courseDao;


    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    REFERENCE JDBCComplex examples
     */

    @Override
    @Transactional
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        final String INSTERT_INTO_COURSE = "INSERT INTO course(courseCode, courseDesc, teacherId) VALUES(?,?,?)";
        jdbcTemplate.update(INSTERT_INTO_COURSE,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId());

        //int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        //course.setCourseId(newId);
        return course;



        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        final String SELECT_ALL_COURSES = "SELECT * FROM course";
        return jdbcTemplate.query(SELECT_ALL_COURSES, new CourseMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE
        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE cid = ?";
            return jdbcTemplate.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE
        final String UPDATE_COURSE = "UPDATE course SET courseCode = ?, courseDesc = ?, teacherId = ? WHERE cid = ?";
        jdbcTemplate.update(UPDATE_COURSE,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId(),
                course.getCourseId());

        //YOUR CODE ENDS HERE
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student "
                + "WHERE course_id = ?";
        jdbcTemplate.update(DELETE_COURSE_STUDENT, id);

        final String DELETE_COURSE = "DELETE FROM course WHERE cid = ?";
        jdbcTemplate.update(DELETE_COURSE, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    @Transactional
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE
        final String DELETE_MEETING_EMPLOYEE_BY_ROOM = "DELETE cs.* FROM course_student cs "
                + "JOIN course c ON cs.course_id = c.id WHERE c.cid = ?";
        jdbcTemplate.update(DELETE_MEETING_EMPLOYEE_BY_ROOM, courseId);

        final String DELETE_MEETING_BY_ROOM = "DELETE FROM course WHERE cid = ?";
        jdbcTemplate.update(DELETE_MEETING_BY_ROOM, courseId);

        /*
        final String DELETE_ROOM = "DELETE FROM room WHERE id = ?";
        jdbcTemplate.update(DELETE_ROOM, courseId);
    */

        //YOUR CODE ENDS HERE
    }
}
