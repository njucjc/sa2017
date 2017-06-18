DROP  TABLE students IF EXISTS;

CREATE TABLE  students (
  stu_id     BIGINT IDENTITY PRIMARY  KEY,
  stu_name   VARCHAR(80),
  stu_major  VARCHAR(80),
  stu_type   VARCHAR(80),
  usual_grade  DOUBLE ,
  design_grade DOUBLE,
  exam_grade   DOUBLE
)