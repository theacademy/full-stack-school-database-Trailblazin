package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    /*
    REFERENCE FLOORING MASTERY
     */

    //YOUR CODE STARTS HERE

    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao){
        this.courseDao = courseDao;
    }



    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        Course courseById = null;
        try {
            courseById= courseDao.findCourseById(id);
        } catch (DataAccessException dae) {
            courseById = new Course();
            courseById.setCourseId(id);
            courseById.setCourseName("Course Not Found");
            courseById.setCourseDesc("Course Not Found");
        }
        return courseById;
        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        if(course.getCourseName().equals("")){
            course.setCourseName("Name blank, course NOT added");
        }
        if(course.getCourseDesc().equals("")){
            course.setCourseDesc("Description blank, course NOT added");
        }
        else {
            return courseDao.createNewCourse(course);
        }
        return course;
        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
        if(id == course.getCourseId()){
            courseDao.updateCourse(course);
        }
        else{
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
        }
        return course;
        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        courseDao.deleteCourse(id);
        System.out.println("Course ID:" + id + " deleted");
        //YOUR CODE ENDS HERE
    }
}
