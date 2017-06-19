package cn.edu.nju.sa2017.batch;

import cn.edu.nju.sa2017.model.Student;
import cn.edu.nju.sa2017.repository.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by njucjc on 2017/6/19.
 */

public class ExcelFileToDatabaseItemWriter implements ItemWriter<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFileToDatabaseItemWriter.class);

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public void write(List<? extends Student> list) throws Exception {
        LOGGER.info("size: "+ list.size());
        list.forEach(studnet-> {
            LOGGER.info("add a student, his id is " + studnet.getId());
            studentRepo.addStudent(studnet);
        });
    }
}
