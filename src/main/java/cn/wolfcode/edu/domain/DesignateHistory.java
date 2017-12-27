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
public class DesignateHistory {
    private Long id;

    private String name;//姓名

    private String tel;//电话

    private String qq;//qq
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date assigntime;//移交时间

    private ClassGrade classId;//意向班级

    private SystemDictionaryItem intentiondegree;//意向程度

	private String minute ;

    private Employee source;//原拥有者

    private Employee target;//先拥有者

	private Long poolId;
}