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

    private Long  id;
    private String name;
    private String major;
    private String type; //正选、重修、跨院系

    private Integer usual;
    private Integer design;
    private Integer exam; //平时成绩

    //public Student() {}

    public Student(
            Long id,
            String name,
            String major,
            String type,

            Integer usual,
            Integer design,
            Integer exam
    ) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.type = type;

        this.usual = usual;
        this.design = design;
        this.exam = exam;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public Integer getDesign() {
        return design;
    }

    public Integer getExam() {
        return exam;
    }

    public Integer getUsual() {
        return usual;
    }

    public void setUsual(Integer usual) {
        this.usual = usual;
    }

    public void setDesign(Integer design) {
        this.design = design;
    }

    public void setExam(Integer exam) {
        this.exam = exam;
    }
}

