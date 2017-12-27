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
public class Examination {
	private Long id ;//考试编号
	private String name ;//姓名
	private Employee saleman ;//营销人员
	private String qq ;//qq
	private String tel ;//电话
	private ClassGrade classId ;//意向班级
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examtime ;//考试时间
	private boolean result ;//考试结果
	private String remark ;//备注
	private Employee handler ;//考试审核人员
	//预约考试的学员
	private Long psId ;
}