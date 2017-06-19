package cn.edu.nju.sa2017.batch;

import cn.edu.nju.sa2017.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by njucjc on 2017/6/19.
 */
public class ExcelFileToDatabaseItemProcessor implements ItemProcessor<String[], Student>  {

    private Logger logger = LoggerFactory.getLogger(ExcelFileToDatabaseItemProcessor.class);

    @Override
    public Student process(String[] tokens) throws Exception {
        Student student = new Student(
                "141220008",
                "Jim",
                "CS",
                "正选",
                10.0,
                20.0,
                70.0
        );
        student.setId(tokens[0]);
        student.setName(tokens[1]);
        student.setMajor(tokens[3]);
        student.setType(tokens[2]);
        student.setUsual(Double.parseDouble(tokens[4]));
        student.setDesign(Double.parseDouble(tokens[5]));
        student.setExam(Double.parseDouble(tokens[6]));
        logger.info("get a student, his id is " + student.getId());

        return student;
    }
}
