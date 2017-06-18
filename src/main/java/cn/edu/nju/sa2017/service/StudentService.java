package cn.edu.nju.sa2017.service;

import com.github.pagehelper.PageInfo;
import cn.edu.nju.sa2017.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by njucjc on 2017/6/17.
 */
public interface StudentService {

    public List<Student> getAllStudents();

    public List<Student> findStudentById(String id);

    public PageInfo<Student> getStudentsPage (Integer pageNum, Integer pageSize);

    public void deleteStudentById(String id);

    public void addStudent (Student student);

    public void updateStudent(Student student);
}
