package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class Course {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String weekday;

    private ClassGrade classGrade;

    private String courseName;

    private Employee leader;

    private Employee teacher;

    private Classroom classroom;

    private String remark;

    private boolean state;


}