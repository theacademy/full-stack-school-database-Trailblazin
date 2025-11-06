package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        Student studentById = null;
        try {
            studentById= studentDao.findStudentById(id);
        } catch (DataAccessException dae) {
            studentById = new Student();
            studentById.setStudentId(id);
            studentById.setStudentFirstName("Student Not Found");
            studentById.setStudentLastName("Student Not Found");
        }
        return studentById;

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if(student.getStudentFirstName().equals("")){
            student.setStudentFirstName("First Name blank, student NOT added");
        }
        if(student.getStudentLastName().equals("")){
            student.setStudentLastName("Last Name blank, student NOT added");
        }
        else {
            return studentDao.createNewStudent(student);
        }
        return student;

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if(id == student.getStudentId()){
            studentDao.updateStudent(student);
        }
        else{
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
        }
        return student;
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE


        studentDao.deleteStudent(id);
        System.out.println("Student ID:" + id + " deleted");

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudentFromCourse(studentId,courseId);

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        studentDao.addStudentToCourse(studentId,courseId);
        //YOUR CODE ENDS HERE
    }
}
