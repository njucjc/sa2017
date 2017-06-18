package cn.edu.nju.sa2017.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by njucjc on 2017/6/17.
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student extends PageHelperEntity {

    private String  id;
    private String name;
    private String major;
    private String type; //正选、重修、跨院系

    private Double usual;
    private Double design;
    private Double exam; //平时成绩

    //public Student() {}

    public Student(
            String id,
            String name,
            String major,
            String type,

            Double usual,
            Double design,
            Double exam
    ) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.type = type;

        this.usual = usual;
        this.design = design;
        this.exam = exam;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public Double getDesign() {
        return design;
    }

    public Double getExam() {
        return exam;
    }

    public Double getUsual() {
        return usual;
    }

    public void setUsual(Double usual) {
        this.usual = usual;
    }

    public void setDesign(Double design) {
        this.design = design;
    }

    public void setExam(Double exam) {
        this.exam = exam;
    }
}

