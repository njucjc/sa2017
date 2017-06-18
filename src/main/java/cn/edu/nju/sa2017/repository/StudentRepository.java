package cn.edu.nju.sa2017.repository;

import cn.edu.nju.sa2017.model.Student;
import java.util.List;

/**
 * Created by njucjc on 2017/6/17.
 */
public interface StudentRepository {

    List<Student> findStudentById(String id);

    List<Student> getAllStudents();

    Integer addStudent(Student student);

    Integer updateStudent(Student student);

    Integer deleteStudentById(String id);
}
