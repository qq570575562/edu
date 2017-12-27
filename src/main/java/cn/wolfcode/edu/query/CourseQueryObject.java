package cn.wolfcode.edu.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class CourseQueryObject extends QueryObject {
    private String classGrade;
    private String classroom;
    private String teacher;
    private String courseName;
    private String sort;
    private Date date;
    private Long classId;
    public String getSort(){
        return "cs."+sort;
    }
    private String order;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
