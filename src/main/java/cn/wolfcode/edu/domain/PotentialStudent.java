package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class PotentialStudent {
	//潜在学生普通字段
	private Long id ;
	private String name ;//姓名
	private Integer age ;//年龄
	private boolean gender ;//性别
	private String tel ;//电话
	private String qq ;//qq
	private boolean tail ;//是否跟踪
	private String email ;//email
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date filingTime ;//建档日期
	private String weiChart ;//微信
	private String school ;//学校
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inputTime ;//录入时间
	private String address ;//地址
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date followTime ;//下次跟进时间
	private String major ;//专业
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date aboutTime ;//约访时间
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entranceTime ;//入学时间
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastTime ;
	//关联其他表的数据  这个使用内联查询
	private FormalStudent student ;//介绍学员
	private ClassGrade classId ;//意向班级
	private Universitytrace universitytrace;//关联大客户表
	//通过数据字典查询
	private Employee sale ;//营销人员
	private SystemDictionaryItem source ;//来源
	private SystemDictionaryItem intentionSchool;//意向校区
	private SystemDictionaryItem intentionSubject;//意向学科
	private SystemDictionaryItem education;//学历
	private SystemDictionaryItem intentionDegree;//意向程度
	//下面是判断的数据
	private boolean hasPaid;//零付款
	private boolean clientType;//客户类型   线上 线下
	private boolean state;//状态    正式学员和非正式学员
	//两个文本框
	private String attention;//关注问题
	private String remark ;//备注
	private Integer tailnum ;//跟踪次数
}
