package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class StudentTail {
    private Long id;
    private String name;//qq
    private String qq;//qq
    private String email;//邮箱
    private String tel;//电话
    private boolean state;//状态
	private Integer tailnum ;//跟踪次数
	private Long pid ;//关联潜在学员

    private Employee sale;//营销员  连员工表
    private Universitytrace university;//学校 连学校表
    private ClassGrade classId;//意向班级  连班级表
	//下面是连接数据字典
    private SystemDictionaryItem goal;//跟进目的
    private SystemDictionaryItem exchange;//交流方式
    private SystemDictionaryItem intentiondegree;//意向程度

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date abouttime;//约谈时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lasttime;//上次跟进时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date followtime;//下次跟进时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thistime;//本次跟进时间

    private String duration;//持续时间
    private String digest;//摘要
    private String content;//内容

	private String audioex;//审核说明
	private SystemDictionaryItem appraise; //审核评价
}