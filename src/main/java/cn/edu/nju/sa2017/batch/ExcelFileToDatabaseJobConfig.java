package cn.edu.nju.sa2017.batch;

import cn.edu.nju.sa2017.model.Student;
import cn.edu.nju.sa2017.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.mapping.PassThroughRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * Created by njucjc on 2017/6/18.
 * Reference: https://github.com/spring-projects/spring-batch-extensions
 */

@Configuration
@EnableBatchProcessing
public class ExcelFileToDatabaseJobConfig {

    private Logger logger = LoggerFactory.getLogger(ExcelFileToDatabaseJobConfig.class);

    private static final int LINES_TO_SKIP = 4;

    private static final int CHUNK_SIZE = 16;

   // private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;


    public static class ImportStudentsFromExcelItemProcessor implements ItemProcessor<String[], Student> {

        private Logger logger = LoggerFactory.getLogger(ImportStudentsFromExcelItemProcessor.class);

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
    public static class ImportStudentsFromExcelItemWriter implements ItemWriter<Student> {
        private static final Logger LOGGER = LoggerFactory.getLogger(ImportStudentsFromExcelItemWriter.class);

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

    @Component
    public static class importStudentsFromExcelJobExecutionListener implements JobExecutionListener {

        @Override
        public void beforeJob(JobExecution jobExecution) {

        }

        @Override
        public void afterJob(JobExecution jobExecution) {

        }
    }

    @Bean
    public ItemReader<String[]> excelStudentReader() throws Exception {
        PoiItemReader<String[]> reader = new PoiItemReader<String[]>();
        reader.setLinesToSkip(LINES_TO_SKIP);
        reader.setResource(new ClassPathResource("students.xlsx"));
        reader.setRowMapper(new PassThroughRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<String[], Student> importStudentsFromExcelProcessor() {
        return new ImportStudentsFromExcelItemProcessor();
    }

    @Bean
    public ItemWriter<Student> excelStudentWriter() {
        return new ImportStudentsFromExcelItemWriter();
    }

    @Bean
    public Step importStudentsFromExcelStep() throws Exception {
        return stepBuilderFactory.get("importStudentsFromExcelStep")
                .<String[], Student> chunk(CHUNK_SIZE)
                .reader(excelStudentReader())
                .processor(importStudentsFromExcelProcessor())
                .writer(excelStudentWriter())
                .build();
    }

    @Bean
    public Job importStudentsFromExcelJob(importStudentsFromExcelJobExecutionListener listener) throws Exception {
        return jobBuilderFactory.get("importStudentsFromExcelJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(importStudentsFromExcelStep())
                .end()
                .build();
    }


}
