package cn.edu.nju.sa2017.rest;

import cn.edu.nju.sa2017.service.StudentServiceImpl;
import com.github.pagehelper.PageInfo;
import cn.edu.nju.sa2017.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by njucjc on 2017/6/17.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

   @Autowired
   StudentServiceImpl studentService;

   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   public PageInfo<Student> getAllStudents() {
       System.out.println("Get all students");
       PageInfo<Student> list = studentService.getStudentsPage(1,10);
       return list;
   }

    @RequestMapping(value = "/find",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Student> findStudentById(Long id) {
        System.out.println("Find student by id: " + id);
        List<Student> studentList = studentService.findStudentById(id);
        return new PageInfo<Student>(studentList);
    }

    @RequestMapping(value = "/pageInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> getStudentPage(Integer pageNum, Integer pageSize) {
        System.out.println("Get pageInfo");
        PageInfo<Student> list = studentService.getStudentsPage(pageNum,pageSize);
        return  list;
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> addStudent(Student student){
        System.out.println("add a student, his id is " + student.getId());
        studentService.addStudent(student);
        List<Student> studentList = studentService.getAllStudents();
        return new PageInfo<Student>(studentList);
    }

    @RequestMapping(value =  "/delete",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> deleteStudent (Long id){
        System.out.println("Delete student, his id is " + id);
        studentService.deleteStudentById(id);
        List<Student> studentList = studentService.getAllStudents();
        return new PageInfo<Student>(studentList);
    }

    @RequestMapping( value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> updateStudent (Student student){
        System.out.println("Update student, his id is " + student.getId());
        studentService.updateStudent(student);
        List<Student> studentList = studentService.getAllStudents();
        return new PageInfo<Student>(studentList);
    }


}
