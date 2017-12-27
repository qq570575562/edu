package cn.wolfcode.edu.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class PotentialStudentQueryObject extends  QueryObject{
	private Integer minAge;
	private Integer maxAge;
	private Long intentionSubjectId ;
	private Long intentionSchoolId ;
	private Long intentionDegreeId ;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime ;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime ;
	private Boolean state ;
}
