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



/**
 * Created by njucjc on 2017/6/18.
 * Reference: https://github.com/spring-projects/spring-batch-extensions
 */

@Configuration
@EnableBatchProcessing
public class ExcelFileToDatabaseJobConfig {

    private Logger logger = LoggerFactory.getLogger(ExcelFileToDatabaseJobConfig.class);

    private static final int LINES_TO_SKIP = 4;

    private static final int CHUNK_SIZE = 4;

   // private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;


/*    public static class ExcelFileToDatabaseItemProcessor implements ItemProcessor<String[], Student> {

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
*/
    @Component
    public static class ExcelFileToDatabaseJobExecutionListener implements JobExecutionListener {

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
    public ItemProcessor<String[], Student> excelFileToDatabaseItemProcessor() {
        return new ExcelFileToDatabaseItemProcessor();
    }

    @Bean
    public ItemWriter<Student> excelStudentWriter() {
        return new ExcelFileToDatabaseItemWriter();
    }

    @Bean
    public Step excelFileToDatabaseStep() throws Exception {
        return stepBuilderFactory.get("excelFileToDatabaseStep")
                .<String[], Student> chunk(CHUNK_SIZE)
                .reader(excelStudentReader())
                .processor(excelFileToDatabaseItemProcessor())
                .writer(excelStudentWriter())
                .build();
    }

    @Bean
    public Job excelFileToDatabaseJob(ExcelFileToDatabaseJobExecutionListener listener) throws Exception {
        return jobBuilderFactory.get("excelFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(excelFileToDatabaseStep())
                .end()
                .build();
    }
}
