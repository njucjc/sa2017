DROP  TABLE students IF EXISTS;

CREATE TABLE  students (
  stu_id     BIGINT IDENTITY PRIMARY  KEY,
  stu_name   VARCHAR(80),
  stu_major  VARCHAR(80),
  stu_type   VARCHAR(80),
  usual_grade  INT,
  design_grade INT,
  exam_grade   INT
)