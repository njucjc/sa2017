package cn.edu.nju.sa2017.rest;

import cn.edu.nju.sa2017.service.StudentServiceImpl;
import cn.edu.nju.sa2017.util.TempFile;
import com.github.pagehelper.PageInfo;
import cn.edu.nju.sa2017.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by njucjc on 2017/6/17.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job ExcelFileToDatabaseJob;

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
    public PageInfo<Student> findStudentById(String id) {
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
    public PageInfo<Student> deleteStudent (String id){
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

    //private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";

    @RequestMapping( value = "/import",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> importStudents(@RequestParam(value="filename") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        java.io.File tmpFile = TempFile.createTempFile(file);
        System.out.println(tmpFile.getAbsoluteFile());
        try {
            JobParameters parameters = new JobParametersBuilder().addString("path-to-file",tmpFile.getAbsolutePath()).toJobParameters();
            jobLauncher.run(ExcelFileToDatabaseJob, parameters);
            if(tmpFile.exists()) {
                tmpFile.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        PageInfo<Student> list = studentService.getStudentsPage(1,10);
        return list;
    }


}
