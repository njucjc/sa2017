package cn.edu.nju.sa2017.service;

import com.github.pagehelper.PageInfo;
import cn.edu.nju.sa2017.model.Student;

import java.util.List;

/**
 * Created by njucjc on 2017/6/17.
 */
public interface StudentService {

    public List<Student> getAllStudents();

    public List<Student> findStudentById(Long id);

    public PageInfo<Student> getStudentsPage (Integer pageNum, Integer pageSize);

    public void deleteStudentById(Long id);

    public void addStudent (Student student);

    public void updateStudent(Student student);
}
