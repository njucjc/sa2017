package cn.edu.nju.sa2017.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.edu.nju.sa2017.model.Student;
import cn.edu.nju.sa2017.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by njucjc on 2017/6/17.
 */
@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        PageHelper.startPage(1, 10);
        return studentRepository.getAllStudents();
    }

    public PageInfo<Student> getStudentsPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> student = studentRepository.getAllStudents();
        PageInfo<Student> pageInfo = new PageInfo<>(student);
        return pageInfo;
    }

    public List<Student> findStudentById(String id) {
        return studentRepository.findStudentById(id);
    }

    public void deleteStudentById(String id) {
        studentRepository.deleteStudentById(id);
    }

    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudent(student);
    }
}